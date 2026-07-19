package com.ecommerce.services.impl;

import com.ecommerce.dto.response.OrderResponse;
import com.ecommerce.entities.*;
import com.ecommerce.enums.OrderStatus;
import com.ecommerce.enums.PaymentStatus;
import com.ecommerce.events.OrderPlacedEvent;
import com.ecommerce.mapper.OrderMapper;
import com.ecommerce.repos.*;
import com.ecommerce.services.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl
        implements OrderService {

    private final UserRepository userRepository;

    private final CartRepository cartRepository;

    private final CartItemRepository cartItemRepository;

    private final OrderRepository orderRepository;

    private final OrderItemRepository orderItemRepository;

    private final PaymentRepository paymentRepository;

    private final OrderMapper orderMapper;

    private final ApplicationEventPublisher eventPublisher;

    @Override
    public OrderResponse placeOrder(
            Long userId) {

        log.info(
                "Entering method : OrderServiceImpl.placeOrder()"
        );

        User user =
                userRepository.findById(userId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "User not found"
                                ));

        Cart cart =
                cartRepository.findByUser(user)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Cart not found"
                                ));

        List<CartItem> cartItems =
                cartItemRepository.findByCart(
                        cart
                );

        if (cartItems.isEmpty()) {

            throw new RuntimeException(
                    "Cart is empty"
            );
        }

        Order order = Order.builder()
                .orderNumber(
                        generateOrderNumber()
                )
                .orderDate(
                        LocalDateTime.now()
                )
                .orderStatus(
                        OrderStatus.PLACED
                )
                .totalAmount(
                        cart.getTotalAmount()
                )
                .user(user)
                .build();

        Order savedOrder =
                orderRepository.save(order);

        for (CartItem cartItem : cartItems) {

            OrderItem orderItem =
                    OrderItem.builder()
                            .order(savedOrder)
                            .product(
                                    cartItem.getProduct()
                            )
                            .quantity(
                                    cartItem.getQuantity()
                            )
                            .price(
                                    cartItem.getProduct()
                                            .getPrice()
                            )
                            .build();

            orderItemRepository.save(
                    orderItem
            );
        }

        Payment payment = Payment.builder()
                .amount(
                        savedOrder.getTotalAmount()
                )
                .paymentMethod(
                        "COD"
                )
                .paymentStatus(
                        PaymentStatus.PENDING
                )
                .order(savedOrder)
                .build();

        paymentRepository.save(
                payment
        );

        cartItemRepository.deleteAll(
                cartItems
        );

        cart.setTotalAmount(0.0);

        cartRepository.save(cart);

        eventPublisher.publishEvent(
                new OrderPlacedEvent(
                        savedOrder
                )
        );

        log.info(
                "Order placed successfully. Order Id : {}",
                savedOrder.getId()
        );

        log.info(
                "Exiting method : OrderServiceImpl.placeOrder()"
        );

        return orderMapper.toResponse(
                savedOrder
        );
    }

    @Override
    @Transactional(readOnly = true)
    public OrderResponse getOrderById(
            Long orderId) {

        log.info(
                "Entering method : OrderServiceImpl.getOrderById()"
        );

        Order order =
                orderRepository.findById(orderId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Order not found"
                                ));

        log.info(
                "Exiting method : OrderServiceImpl.getOrderById()"
        );

        return orderMapper.toResponse(
                order
        );
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderResponse> getUserOrders(
            Long userId) {

        log.info(
                "Entering method : OrderServiceImpl.getUserOrders()"
        );

        User user =
                userRepository.findById(userId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "User not found"
                                ));

        List<OrderResponse> orders =
                orderRepository.findByUser(user)
                        .stream()
                        .map(orderMapper::toResponse)
                        .toList();

        log.info(
                "Exiting method : OrderServiceImpl.getUserOrders()"
        );

        return orders;
    }

    private String generateOrderNumber() {

        return "ORD-"
                + UUID.randomUUID()
                .toString()
                .substring(0, 8)
                .toUpperCase();
    }
}