package com.shoplite.order_service.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record OrderUpdateRequestDto(

        @NotNull(message = "customerId ne doit pas être nul")
        Long customerId,

        @NotEmpty(message = "items ne doit pas être vide")

        List<@Valid OrderItemRequestDto> items
) {
}
