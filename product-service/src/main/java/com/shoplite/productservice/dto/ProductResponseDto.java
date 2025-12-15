package com.shoplite.productservice.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponseDto {


    private Long id;
    private String name;
    private String sku;
    private BigDecimal price;
    private Integer quantityInStock;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
