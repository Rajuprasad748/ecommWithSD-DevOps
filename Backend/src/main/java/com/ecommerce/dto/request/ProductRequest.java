package com.ecommerce.dto.request;

import jakarta.validation.constraints.*;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {

    @NotBlank(
            message = "Product name is required"
    )
    private String name;

    @NotBlank(
            message = "Description is required"
    )
    private String description;

    @NotNull(
            message = "Price is required"
    )
    @Positive(
            message = "Price must be positive"
    )
    private Double price;

    @NotNull(
            message = "Stock is required"
    )
    @Min(
            value = 0,
            message =
                    "Stock cannot be negative"
    )
    private Integer stock;

    private String imageUrl;

    @NotNull(
            message = "Category is required"
    )
    private Long categoryId;
}