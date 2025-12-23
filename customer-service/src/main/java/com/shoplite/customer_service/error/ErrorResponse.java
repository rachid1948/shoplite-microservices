package com.shoplite.customer_service.error;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Erreur retournée par l’API")
public class ErrorResponse {

    @Schema(example = "2025-12-23T10:30:00")
    private LocalDateTime timestamp;

    @Schema(example = "404")
    private int status;

    @Schema(example = "CUSTOMER_NOT_FOUND")
    private String error;

    @Schema(example = "Customer not found with id 1")
    private String message;

    @Schema(example = "/api/v1/customers/1")
    private String path;
}
