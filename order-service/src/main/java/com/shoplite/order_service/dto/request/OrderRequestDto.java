package com.shoplite.order_service.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.List;

public record OrderRequestDto(

        @NotNull(message = "customerId ne doit pas être nul")

        @Min(value = 1, message = "customerId doit être >= 1")
        Long customerId,

        @NotBlank(message = "customerName ne doit pas être vide")
        String customerName,

        @Email(message = "customerEmail doit être un email valide")
        String customerEmail,

        @NotEmpty(message = "La commande doit contenir au moins un produit")
        List<@Valid OrderItemRequestDto> items


) { }