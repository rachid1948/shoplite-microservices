package com.shoplite.customer_service.service;

import com.shoplite.customer_service.dto.CustomerRequestDto;
import com.shoplite.customer_service.dto.CustomerResponseDto;

import java.util.List;

public interface CustomerService {

    List<CustomerResponseDto> getAllCustomers();

    CustomerResponseDto getCustomerById(Long id);

    CustomerResponseDto createCustomer(CustomerRequestDto dto);

    CustomerResponseDto updateCustomer(Long id, CustomerRequestDto dto);

    void deleteCustomer(Long id);

    boolean customerExists(Long id);
}
