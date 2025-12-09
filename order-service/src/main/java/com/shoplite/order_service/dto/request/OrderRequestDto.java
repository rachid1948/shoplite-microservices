package com.shoplite.order_service.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class OrderRequestDto {

    @NotBlank
    private String customerId;

    @NotEmpty
    @Valid
    private List<OrderItemRequestDto> items;
}
