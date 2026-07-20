package com.ecommerce.mapper;

import com.ecommerce.dto.response.ProductResponse;
import com.ecommerce.entities.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductResponse toResponse(
            Product product) {

        if (product == null) {
            return null;
        }

        return ProductResponse.builder()
                .productId(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stock(product.getStock())
                .categoryName(
                        product.getCategory() != null
                                ? product.getCategory().getName()
                                : null
                )
                .build();
    }
}