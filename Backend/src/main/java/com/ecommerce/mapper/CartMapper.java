package com.ecommerce.mapper;

import com.ecommerce.dto.response.CartResponse;
import com.ecommerce.entities.Cart;
import org.springframework.stereotype.Component;

@Component
public class CartMapper {

    public CartResponse toResponse(
            Cart cart) {

        if (cart == null) {
            return null;
        }

        return CartResponse.builder()
                .cartId(cart.getId())
                .userId(cart.getUser().getId())
                .totalAmount(cart.getTotalAmount())
                .build();
    }
}