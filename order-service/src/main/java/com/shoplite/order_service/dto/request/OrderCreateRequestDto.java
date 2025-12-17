package com.shoplite.order_service.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record OrderCreateRequestDto(
        @NotNull @Min(1) Long customerId,
        @NotEmpty List<@Valid OrderCreateItemDto> items
) {}

