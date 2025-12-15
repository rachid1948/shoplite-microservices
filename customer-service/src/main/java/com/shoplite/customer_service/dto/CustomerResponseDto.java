package com.shoplite.customer_service.dto;

import java.time.LocalDateTime;

public record CustomerResponseDto(
        Long id,
        String firstName,
        String lastName,
        String email,
        String phone,
        String address,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
