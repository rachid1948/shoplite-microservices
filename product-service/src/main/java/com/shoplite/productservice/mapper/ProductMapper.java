package com.shoplite.productservice.mapper;

import com.shoplite.productservice.dto.ProductRequestDto;
import com.shoplite.productservice.dto.ProductResponseDto;
import com.shoplite.productservice.entity.Product;

public class ProductMapper {

    private ProductMapper() {
        // private constructor to prevent instantiation
    }

    public static Product toEntity(ProductRequestDto dto) {
        if (dto == null) {
            return null;
        }

        return Product.builder()
                .name(dto.getName())
                .sku(dto.getSku())
                .price(dto.getPrice())
                .quantityInStock(dto.getQuantityInStock())
                .description(dto.getDescription())
                .build();
    }

    public static void updateEntityFromDto(ProductRequestDto dto, Product entity) {
        if (dto == null || entity == null) {
            return;
        }

        entity.setName(dto.getName());
        entity.setSku(dto.getSku());
        entity.setPrice(dto.getPrice());
        entity.setQuantityInStock(dto.getQuantityInStock());
        entity.setDescription(dto.getDescription());
    }

    public static ProductResponseDto toResponseDto(Product entity) {
        if (entity == null) {
            return null;
        }

        return ProductResponseDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .sku(entity.getSku())
                .price(entity.getPrice())
                .quantityInStock(entity.getQuantityInStock())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
