package com.ecommerce.services;

import com.raju.ecommerce.dto.response.OrderResponse;

import java.util.List;
public interface OrderService {
    OrderResponse placeOrder();

    OrderResponse getOrderById(Long orderId);

    List<OrderResponse> getMyOrders();

    void cancelOrder(Long orderId);
}
