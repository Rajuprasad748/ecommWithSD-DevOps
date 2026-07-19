package com.ecommerce.services;

import com.ecommerce.dto.response.OrderResponse;

import java.util.List;

public interface OrderService {

    OrderResponse placeOrder(
            Long userId
    );

    OrderResponse getOrderById(
            Long orderId
    );

    List<OrderResponse> getUserOrders(
            Long userId
    );
}