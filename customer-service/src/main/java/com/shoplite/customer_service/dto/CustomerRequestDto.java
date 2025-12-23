package com.shoplite.customer_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CustomerRequestDto(
        @NotBlank(message = "First name is required")
        @Size(max = 100, message = "First name must be at most 100 characters")
        //@Schema(example = "rachid",name = "first")
        String firstName,

        @NotBlank(message = "Last name is required")
        @Size(max = 100, message = "Last name must be at most 100 characters")
        String lastName,

        @NotBlank(message = "Email is required")
        @Email(message = "Email must be valid")
        @Size(max = 255, message = "Email must be at most 255 characters")
        String email,

        @Size(max = 50, message = "Phone must be at most 50 characters")
        String phone,

        @Size(max = 255, message = "Address must be at most 255 characters")
        String address,

        @Size(max = 100, message = "City must be at most 100 characters")
        String city,

        @Size(max = 100, message = "Country must be at most 100 characters")
        String country
) {
}
