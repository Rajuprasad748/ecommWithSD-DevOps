package com.ecommerce.services;

import com.ecommerce.dto.request.AddToCartRequest;
import com.ecommerce.dto.request.UpdateCartItemRequest;
import com.ecommerce.dto.response.CartResponse;

public interface CartService {

    CartResponse getCart(Long userId);
    //CartResponse getCart();

    CartResponse addToCart(
            Long userId,
            AddToCartRequest request
    );

    CartResponse updateCartItem(
            Long userId,
            UpdateCartItemRequest request
    );

    void removeCartItem(
            Long userId,
            Long cartItemId
    );

    void clearCart(
            Long userId
    );
}