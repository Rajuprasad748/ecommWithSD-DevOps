package com.ecommerce.mapper;

import com.ecommerce.dto.response.OrderResponse;
import com.ecommerce.entities.Order;
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
                        String.valueOf(order.getOrderStatus())
                )
                .totalAmount(order.getTotalAmount())
                .build();
    }
}