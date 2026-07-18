package com.ecommerce.services;


import com.ecommerce.dto.request.ProductRequest;
import com..ecommerce.dto.response.ProductResponse;

import java.util.List;

public class ProductService {
    ProductResponse createProduct(ProductRequest request);

    ProductResponse getProductById(Long productId);

    List<ProductResponse> getAllProducts();

    ProductResponse updateProduct(
            Long productId,
            ProductRequest request
    );

    void deleteProduct(Long productId);

    List<ProductResponse> searchProducts(String keyword);
}
