package com.ecommerce.services.impl;

import com.ecommerce.dto.request.ProductRequest;
import com.ecommerce.dto.response.ProductResponse;
import com.ecommerce.entities.Category;
import com.ecommerce.entities.Product;
import com.ecommerce.mapper.ProductMapper;
import com.ecommerce.repos.CategoryRepository;
import com.ecommerce.repos.ProductRepository;
import com.ecommerce.services.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl
        implements ProductService {

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    private final ProductMapper productMapper;

    @Override
    public ProductResponse createProduct(
            ProductRequest request) {

        log.info(
                "Entering method : ProductServiceImpl.createProduct()"
        );

        Category category =
                categoryRepository.findById(
                        request.getCategoryId()
                ).orElseThrow(() ->
                        new RuntimeException(
                                "Category not found"
                        ));

        Product product = Product.builder()
                .name(request.getName())
                .description(
                        request.getDescription()
                )
                .price(request.getPrice())
                .stock(request.getStock())
                .imageUrl(request.getImageUrl())
                .category(category)
                .build();

        Product savedProduct =
                productRepository.save(product);

        log.info(
                "Product created successfully. Product Id : {}",
                savedProduct.getId()
        );

        log.info(
                "Exiting method : ProductServiceImpl.createProduct()"
        );

        return productMapper.toResponse(
                savedProduct
        );
    }

    @Override
    @Transactional(readOnly = true)
    public ProductResponse getProductById(
            Long productId) {

        log.info(
                "Entering method : ProductServiceImpl.getProductById()"
        );

        Product product =
                productRepository.findById(productId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Product not found"
                                ));

        log.info(
                "Exiting method : ProductServiceImpl.getProductById()"
        );

        return productMapper.toResponse(
                product
        );
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponse> getAllProducts() {

        log.info(
                "Entering method : ProductServiceImpl.getAllProducts()"
        );

        List<ProductResponse> products =
                productRepository.findAll()
                        .stream()
                        .map(productMapper::toResponse)
                        .toList();

        log.info(
                "Exiting method : ProductServiceImpl.getAllProducts()"
        );

        return products;
    }

    @Override
    public ProductResponse updateProduct(
            Long productId,
            ProductRequest request) {

        log.info(
                "Entering method : ProductServiceImpl.updateProduct()"
        );

        Product product =
                productRepository.findById(productId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Product not found"
                                ));

        Category category =
                categoryRepository.findById(
                        request.getCategoryId()
                ).orElseThrow(() ->
                        new RuntimeException(
                                "Category not found"
                        ));

        product.setName(
                request.getName()
        );

        product.setDescription(
                request.getDescription()
        );

        product.setPrice(
                request.getPrice()
        );

        product.setStock(
                request.getStock()
        );

        product.setImageUrl(
                request.getImageUrl()
        );

        product.setCategory(
                category
        );

        Product updatedProduct =
                productRepository.save(product);

        log.info(
                "Product updated successfully. Product Id : {}",
                updatedProduct.getId()
        );

        log.info(
                "Exiting method : ProductServiceImpl.updateProduct()"
        );

        return productMapper.toResponse(
                updatedProduct
        );
    }

    @Override
    public void deleteProduct(
            Long productId) {

        log.info(
                "Entering method : ProductServiceImpl.deleteProduct()"
        );

        Product product =
                productRepository.findById(productId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Product not found"
                                ));

        productRepository.delete(product);

        log.info(
                "Product deleted successfully. Product Id : {}",
                productId
        );

        log.info(
                "Exiting method : ProductServiceImpl.deleteProduct()"
        );
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponse> searchProducts(
            String keyword) {

        log.info(
                "Entering method : ProductServiceImpl.searchProducts()"
        );

        List<ProductResponse> products =
                productRepository
                        .findByNameContainingIgnoreCase(
                                keyword
                        )
                        .stream()
                        .map(productMapper::toResponse)
                        .toList();

        log.info(
                "Exiting method : ProductServiceImpl.searchProducts()"
        );

        return products;
    }
}