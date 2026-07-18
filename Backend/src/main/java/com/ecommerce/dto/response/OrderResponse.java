package com.ecommerce.dto.response;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

    private Long orderId;

    private String orderNumber;

    private String orderStatus;

    private Double totalAmount;
}
