package com.shoplite.order_service.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

public record OrderRequestDto(

        @NotNull(message = "customerId ne doit pas être nul")
        @Min(value = 1, message = "customerId doit être >= 1")
        Long customerId,

        @NotEmpty(message = "items ne doit pas être vide")
        List<OrderItemRequestDto> items
) { }