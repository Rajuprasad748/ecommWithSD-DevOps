package com.ecommerce.mapper;

import com.ecommerce.dto.response.OrderResponse;
import com.ecommerce.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public OrderResponse toResponse(
            Order order) {

        if (order == null) {
            return null;
        }

        return OrderResponse.builder()
                .orderId(order.getId())
                .orderNumber(order.getOrderNumber())
                .orderStatus(
                        order.getOrderStatus().name()
                )
                .totalAmount(order.getTotalAmount())
                .build();
    }
}