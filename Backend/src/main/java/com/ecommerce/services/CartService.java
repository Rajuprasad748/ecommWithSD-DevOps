package com.ecommerce.services;
import com.ecommerce.dto.request.AddToCartRequest;
import com.ecommerce.dto.request.UpdateCartItemRequest;
import com.ecommerce.dto.response.CartResponse;
public class CartService {
    CartResponse getCart();

    CartResponse addToCart(AddToCartRequest request);

    CartResponse updateCartItem(UpdateCartItemRequest request);

    void removeCartItem(Long cartItemId);

    void clearCart();
}
