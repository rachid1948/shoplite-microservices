package com.shoplite.customer_service.controller;


import com.shoplite.customer_service.dto.CustomerRequestDto;
import com.shoplite.customer_service.dto.CustomerResponseDto;
import com.shoplite.customer_service.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CustomerController implements CustomerApi {

    private final CustomerService customerService;

    @Override
    public ResponseEntity<List<CustomerResponseDto>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @Override
    public ResponseEntity<CustomerResponseDto> getCustomer(Long id) {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    @Override
    public ResponseEntity<CustomerResponseDto> createCustomer(@Valid CustomerRequestDto request) {
        CustomerResponseDto created = customerService.createCustomer(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @Override
    public ResponseEntity<CustomerResponseDto> update(Long id, @Valid CustomerRequestDto dto) {
        return ResponseEntity.ok(customerService.updateCustomer(id, dto));
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Boolean> customerExists(Long id) {
        return ResponseEntity.ok(customerService.customerExists(id));
    }
}
