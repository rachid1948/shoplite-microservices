package com.shoplite.order_service.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

public record OrderItemRequestDto(
        @NotNull(message = "productId ne doit pas être nul")
        Long productId,

        @NotBlank(message = "productName ne doit pas être vide")
        String productName,

        @NotNull(message = "quantity ne doit pas être nulle")
        @Positive(message = "quantity doit être > 0")
        Integer quantity,

        @NotNull(message = "unitPrice ne doit pas être nul")
        @Positive(message = "unitPrice doit être > 0")
        BigDecimal unitPrice
) {}
