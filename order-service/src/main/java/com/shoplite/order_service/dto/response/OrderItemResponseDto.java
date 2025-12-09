package com.shoplite.order_service.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class OrderItemResponseDto {

    private Long id;
    private Long productId;
    private String sku;
    private Integer quantity;
    private BigDecimal unitPrice;
}
