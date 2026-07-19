package com.ecommerce.service.impl;

import com.ecommerce.dto.request.AddToCartRequest;
import com.ecommerce.dto.request.UpdateCartItemRequest;
import com.ecommerce.dto.response.CartResponse;
import com.ecommerce.entities.Cart;
import com.ecommerce.entities.CartItem;
import com.ecommerce.entities.Product;
import com.ecommerce.entities.User;
import com.ecommerce.mapper.CartMapper;
import com.ecommerce.repos.CartItemRepository;
import com.ecommerce.repos.CartRepository;
import com.ecommerce.repos.ProductRepository;
import com.ecommerce.repos.UserRepository;
import com.ecommerce.services.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CartServiceImpl implements CartService {

    private final UserRepository userRepository;

    private final ProductRepository productRepository;

    private final CartRepository cartRepository;

    private final CartItemRepository cartItemRepository;

    private final CartMapper cartMapper;

    @Override
    public CartResponse getCart(
            Long userId) {

        log.info(
                "Entering method : CartServiceImpl.getCart()"
        );

        User user = getUser(userId);

        Cart cart = cartRepository
                .findByUser(user)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Cart not found"
                        ));

        calculateCartTotal(cart);

        log.info(
                "Exiting method : CartServiceImpl.getCart()"
        );

        return cartMapper.toResponse(cart);
    }

    @Override
    public CartResponse addToCart(
            Long userId,
            AddToCartRequest request) {

        log.info(
                "Entering method : CartServiceImpl.addToCart()"
        );

        User user = getUser(userId);

        Cart cart = cartRepository
                .findByUser(user)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Cart not found"
                        ));

        Product product =
                productRepository.findById(
                        request.getProductId()
                ).orElseThrow(() ->
                        new RuntimeException(
                                "Product not found"
                        ));

        CartItem cartItem =
                cartItemRepository
                        .findByCartAndProduct(
                                cart,
                                product
                        )
                        .orElse(null);

        if (cartItem != null) {

            cartItem.setQuantity(
                    cartItem.getQuantity()
                            + request.getQuantity()
            );

        } else {

            cartItem = CartItem.builder()
                    .cart(cart)
                    .product(product)
                    .quantity(
                            request.getQuantity()
                    )
                    .build();
        }

        cartItemRepository.save(cartItem);

        calculateCartTotal(cart);

        log.info(
                "Product added to cart successfully"
        );

        log.info(
                "Exiting method : CartServiceImpl.addToCart()"
        );

        return cartMapper.toResponse(cart);
    }

    @Override
    public CartResponse updateCartItem(
            Long userId,
            UpdateCartItemRequest request) {

        log.info(
                "Entering method : CartServiceImpl.updateCartItem()"
        );

        User user = getUser(userId);

        Cart cart = cartRepository
                .findByUser(user)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Cart not found"
                        ));

        CartItem cartItem =
                cartItemRepository.findById(
                        request.getCartItemId()
                ).orElseThrow(() ->
                        new RuntimeException(
                                "Cart Item not found"
                        ));

        cartItem.setQuantity(
                request.getQuantity()
        );

        cartItemRepository.save(cartItem);

        calculateCartTotal(cart);

        log.info(
                "Cart item updated successfully"
        );

        log.info(
                "Exiting method : CartServiceImpl.updateCartItem()"
        );

        return cartMapper.toResponse(cart);
    }

    @Override
    public void removeCartItem(
            Long userId,
            Long cartItemId) {

        log.info(
                "Entering method : CartServiceImpl.removeCartItem()"
        );

        User user = getUser(userId);

        Cart cart = cartRepository
                .findByUser(user)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Cart not found"
                        ));

        CartItem cartItem =
                cartItemRepository.findById(
                        cartItemId
                ).orElseThrow(() ->
                        new RuntimeException(
                                "Cart Item not found"
                        ));

        cartItemRepository.delete(cartItem);

        calculateCartTotal(cart);

        log.info(
                "Cart item removed successfully"
        );

        log.info(
                "Exiting method : CartServiceImpl.removeCartItem()"
        );
    }

    @Override
    public void clearCart(
            Long userId) {

        log.info(
                "Entering method : CartServiceImpl.clearCart()"
        );

        User user = getUser(userId);

        Cart cart = cartRepository
                .findByUser(user)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Cart not found"
                        ));

        List<CartItem> cartItems =
                cartItemRepository.findByCart(
                        cart
                );

        cartItemRepository.deleteAll(
                cartItems
        );

        cart.setTotalAmount(0.0);

        cartRepository.save(cart);

        log.info(
                "Cart cleared successfully"
        );

        log.info(
                "Exiting method : CartServiceImpl.clearCart()"
        );
    }

    private User getUser(
            Long userId) {

        return userRepository
                .findById(userId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "User not found"
                        ));
    }

    private void calculateCartTotal(
            Cart cart) {

        List<CartItem> cartItems =
                cartItemRepository.findByCart(
                        cart
                );

        double total = cartItems.stream()
                .mapToDouble(item ->
                        item.getProduct().getPrice()
                                * item.getQuantity()
                )
                .sum();

        cart.setTotalAmount(total);

        cartRepository.save(cart);
    }
}