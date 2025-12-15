package com.shoplite.customer_service.service.impl;

import com.shoplite.customer_service.dto.CustomerRequestDto;
import com.shoplite.customer_service.dto.CustomerResponseDto;
import com.shoplite.customer_service.entity.Customer;
import com.shoplite.customer_service.mapper.CustomerMapper;
import com.shoplite.customer_service.repository.CustomerRepository;
import com.shoplite.customer_service.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public List<CustomerResponseDto> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::toResponseDto)
                .toList();
    }

    @Override
    public CustomerResponseDto getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id " + id));

        return customerMapper.toResponseDto(customer);
    }

    @Override
    public CustomerResponseDto createCustomer(CustomerRequestDto dto) {
        Customer entity = customerMapper.toEntity(dto);
        Customer saved = customerRepository.save(entity);
        return customerMapper.toResponseDto(saved);
    }

    @Override
    public CustomerResponseDto updateCustomer(Long id, CustomerRequestDto dto) {
        Customer existing = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id " + id));

        customerMapper.updateEntityFromDto(dto, existing);

        Customer updated = customerRepository.save(existing);
        return customerMapper.toResponseDto(updated);
    }

    @Override
    public void deleteCustomer(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new RuntimeException("Customer not found with id " + id);
        }
        customerRepository.deleteById(id);
    }

    @Override
    public boolean customerExists(Long id) {
        return customerRepository.existsById(id);
    }
}
