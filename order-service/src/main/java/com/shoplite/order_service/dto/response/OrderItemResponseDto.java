package com.shoplite.order_service.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

public record OrderItemResponseDto(
        Long id,
        Long productId,
        String productName,
        BigDecimal unitPrice,
        Integer quantity,
        BigDecimal lineTotal
) { }

