package com.shoplite.productservice.controller;


import com.shoplite.productservice.dto.ProductRequestDto;
import com.shoplite.productservice.dto.ProductResponseDto;
import com.shoplite.productservice.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController implements ProductApi {

    private final ProductService productService;

    @Override
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("product-service: OK");
    }

    @Override
    public ResponseEntity<ProductResponseDto> createProduct(@Valid ProductRequestDto requestDto) {
        ProductResponseDto created = productService.createProduct(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @Override
    public ResponseEntity<ProductResponseDto> updateProduct(Long id, @Valid ProductRequestDto requestDto) {
        ProductResponseDto updated = productService.updateProduct(id, requestDto);
        return ResponseEntity.ok(updated);
    }

    @Override
    public ResponseEntity<Void> deleteProduct(Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<ProductResponseDto> getProductById(Long id) {
        ProductResponseDto product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @Override
    public ResponseEntity<ProductResponseDto> getProductBySku(String sku) {
        ProductResponseDto product = productService.getProductBySku(sku);
        return ResponseEntity.ok(product);
    }

    @Override
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
        List<ProductResponseDto> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }
}
