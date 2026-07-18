package com.ecommerce.controllers;

import com.ecommerce.dto.request.ProductRequest;
import com.ecommerce.dto.response.ProductResponse;
import com.ecommerce.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(
            @Valid @RequestBody ProductRequest request) {

        log.info(
                "Entering method : ProductController.createProduct()"
        );

        ProductResponse response =
                productService.createProduct(request);

        log.info(
                "Exiting method : ProductController.createProduct()"
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> getProductById(
            @PathVariable Long productId) {

        log.info(
                "Entering method : ProductController.getProductById() | productId={}",
                productId
        );

        ProductResponse response =
                productService.getProductById(productId);

        log.info(
                "Exiting method : ProductController.getProductById()"
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {

        log.info(
                "Entering method : ProductController.getAllProducts()"
        );

        List<ProductResponse> products =
                productService.getAllProducts();

        log.info(
                "Exiting method : ProductController.getAllProducts()"
        );

        return ResponseEntity.ok(products);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductResponse> updateProduct(
            @PathVariable Long productId,
            @Valid @RequestBody ProductRequest request) {

        log.info(
                "Entering method : ProductController.updateProduct() | productId={}",
                productId
        );

        ProductResponse response =
                productService.updateProduct(productId, request);

        log.info(
                "Exiting method : ProductController.updateProduct()"
        );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(
            @PathVariable Long productId) {

        log.info(
                "Entering method : ProductController.deleteProduct() | productId={}",
                productId
        );

        productService.deleteProduct(productId);

        log.info(
                "Exiting method : ProductController.deleteProduct()"
        );

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductResponse>> searchProducts(
            @RequestParam String keyword) {

        log.info(
                "Entering method : ProductController.searchProducts() | keyword={}",
                keyword
        );

        List<ProductResponse> products =
                productService.searchProducts(keyword);

        log.info(
                "Exiting method : ProductController.searchProducts()"
        );

        return ResponseEntity.ok(products);
    }
}
