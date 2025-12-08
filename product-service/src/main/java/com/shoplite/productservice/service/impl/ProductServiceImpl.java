package com.shoplite.productservice.service.impl;

import com.shoplite.productservice.dto.ProductRequestDto;
import com.shoplite.productservice.dto.ProductResponseDto;
import com.shoplite.productservice.entity.Product;
import com.shoplite.productservice.exception.DuplicateSkuException;
import com.shoplite.productservice.exception.ProductNotFoundException;
import com.shoplite.productservice.mapper.ProductMapper;
import com.shoplite.productservice.repository.ProductRepository;
import com.shoplite.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ProductResponseDto createProduct(ProductRequestDto request) {
        if (productRepository.existsBySku(request.getSku())) {
            throw new DuplicateSkuException(request.getSku());
        }

        Product product = ProductMapper.toEntity(request);
        product = productRepository.save(product);

        return ProductMapper.toResponseDto(product);
    }

    @Override
    public ProductResponseDto updateProduct(Long id, ProductRequestDto request) {

        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        // ici tu peux mettre une méthode mapper pour update
        existing.setName(request.getName());
        existing.setSku(request.getSku());
        existing.setPrice(request.getPrice());
        existing.setQuantityInStock(request.getQuantityInStock());
        existing.setDescription(request.getDescription());
        // existing.setUpdatedAt(LocalDateTime.now()); // plus tard avec l’audit

        Product saved = productRepository.save(existing);

        return ProductMapper.toResponseDto(saved);
    }


    @Override
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException(id);
        }

        productRepository.deleteById(id);
    }


    @Override
    public ProductResponseDto getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        return ProductMapper.toResponseDto(product);
    }

    @Override
    public ProductResponseDto getProductBySku(String sku) {
        Product product = productRepository.findBySku(sku)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        return ProductMapper.toResponseDto(product);
    }

    @Override
    public List<ProductResponseDto> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(ProductMapper::toResponseDto)
                .collect(Collectors.toList());
    }
}
