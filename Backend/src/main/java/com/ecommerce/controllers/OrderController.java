package com.ecommerce.controllers;
import com.ecommerce.services.OrderService;
import com.ecommerce.dto.response.OrderResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController    {
    private final OrderService orderService;

    @PostMapping("/place")
    public ResponseEntity<OrderResponse> placeOrder() {

        log.info(
                "Entering method : OrderController.placeOrder()"
        );

        OrderResponse response =
                orderService.placeOrder(1L);

        log.info(
                "Exiting method : OrderController.placeOrder()"
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> getOrderById(
            @PathVariable Long orderId) {

        log.info(
                "Entering method : OrderController.getOrderById() | orderId={}",
                orderId
        );

        OrderResponse response =
                orderService.getOrderById(orderId);

        log.info(
                "Exiting method : OrderController.getOrderById()"
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getMyOrders() {

        log.info(
                "Entering method : OrderController.getMyOrders()"
        );

        List<OrderResponse> orders =
                orderService.getUserOrders(2L);

        log.info(
                "Exiting method : OrderController.getMyOrders()"
        );

        return ResponseEntity.ok(orders);
    }

    @PutMapping("/{orderId}/cancel")
    public ResponseEntity<String> cancelOrder(
            @PathVariable Long orderId) {

        log.info(
                "Entering method : OrderController.cancelOrder() | orderId={}",
                orderId
        );

        orderService.cancelOrder(orderId);

        log.info(
                "Exiting method : OrderController.cancelOrder()"
        );

        return ResponseEntity.ok(
                "Order cancelled successfully"
        );
    }
}
