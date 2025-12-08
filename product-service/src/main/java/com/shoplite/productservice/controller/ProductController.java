package com.shoplite.productservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Value("${product.message}")
    private String productMessage;

    @GetMapping("/api/v1/products/ping")
    public String ping() {
        return productMessage;
    }
}