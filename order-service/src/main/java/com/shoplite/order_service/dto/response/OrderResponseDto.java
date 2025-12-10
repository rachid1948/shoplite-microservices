package com.shoplite.order_service.dto.response;

import com.shoplite.order_service.domain.enums.OrderStatus;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderResponseDto(
        Long id,
        String orderNumber,
        Long customerId,
        OrderStatus status,
        BigDecimal totalAmount,
        List<OrderItemResponseDto> items,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) { }