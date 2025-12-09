package com.shoplite.order_service.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderItemRequestDto {

    @NotNull
    private Long productId;

    @NotBlank
    private String sku;

    @NotNull
    @Min(1)
    private Integer quantity;
}
