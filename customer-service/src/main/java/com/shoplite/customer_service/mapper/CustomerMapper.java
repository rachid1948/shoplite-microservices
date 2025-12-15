package com.shoplite.customer_service.mapper;

import com.shoplite.customer_service.dto.CustomerRequestDto;
import com.shoplite.customer_service.dto.CustomerResponseDto;
import com.shoplite.customer_service.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    // Entity -> Response DTO
    public CustomerResponseDto toResponseDto(Customer customer) {
        return new CustomerResponseDto(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getPhone(),
                customer.getAddress(),
                customer.getCreatedAt(),
                customer.getUpdatedAt()
        );
    }

    // Request DTO -> nouvelle Entity (create)
    public Customer toEntity(CustomerRequestDto dto) {
        Customer customer = new Customer();
        customer.setFirstName(dto.firstName());
        customer.setLastName(dto.lastName());
        customer.setEmail(dto.email());
        customer.setPhone(dto.phone());
        customer.setAddress(dto.address());
        // id / createdAt / updatedAt seront gérés par JPA
        return customer;
    }

    // Request DTO -> mise à jour d’une Entity existante (update)
    public void updateEntityFromDto(CustomerRequestDto dto, Customer customer) {
        customer.setFirstName(dto.firstName());
        customer.setLastName(dto.lastName());
        customer.setEmail(dto.email());
        customer.setPhone(dto.phone());
        customer.setAddress(dto.address());

    }
}
