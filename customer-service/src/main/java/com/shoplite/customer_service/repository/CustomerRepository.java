package com.shoplite.customer_service.repository;

import com.shoplite.customer_service.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // Pour chercher un client par email
    Optional<Customer> findByEmailIgnoreCase(String email);

    // Pour vérifier si un email existe déjà (utile pour la création)
    boolean existsByEmailIgnoreCase(String email);
}
