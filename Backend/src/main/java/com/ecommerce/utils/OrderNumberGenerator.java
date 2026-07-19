package com.ecommerce.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class OrderNumberGenerator {

    public String generateOrderNumber() {

        log.info(
                "Entering method : OrderNumberGenerator.generateOrderNumber()"
        );

        String orderNumber =
                "ORD-" +
                        UUID.randomUUID()
                                .toString()
                                .substring(0, 8)
                                .toUpperCase();

        log.info(
                "Generated Order Number : {}",
                orderNumber
        );

        log.info(
                "Exiting method : OrderNumberGenerator.generateOrderNumber()"
        );

        return orderNumber;
    }
}