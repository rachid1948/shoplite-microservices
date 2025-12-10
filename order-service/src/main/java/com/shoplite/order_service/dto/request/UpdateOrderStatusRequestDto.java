package com.shoplite.order_service.dto.request;

import com.shoplite.order_service.domain.enums.OrderStatus;
import jakarta.validation.constraints.NotNull;

public record UpdateOrderStatusRequestDto(
        @NotNull(message = "status ne doit pas être nul")
        OrderStatus status
) {}
