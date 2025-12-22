package com.shoplite.order_service.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record OrderCreateItemDto(
        @NotNull @Min(1) Long productId,
        @NotNull @Positive Integer quantity
) {}

