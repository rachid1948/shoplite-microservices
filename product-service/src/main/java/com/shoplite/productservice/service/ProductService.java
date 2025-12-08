package com.shoplite.productservice.service;

import com.shoplite.productservice.dto.ProductRequestDto;
import com.shoplite.productservice.dto.ProductResponseDto;

import java.util.List;

public interface ProductService {

    ProductResponseDto createProduct(ProductRequestDto dto);

    ProductResponseDto updateProduct(Long id, ProductRequestDto dto);

    void deleteProduct(Long id);

    ProductResponseDto getProductById(Long id);

    ProductResponseDto getProductBySku(String sku);

    List<ProductResponseDto> getAllProducts();
}
