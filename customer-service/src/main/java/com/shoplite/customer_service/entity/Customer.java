package com.shoplite.customer_service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Prénom
    @Column(nullable = false, length = 100)
    private String firstName;

    // Nom
    @Column(nullable = false, length = 100)
    private String lastName;

    // Email unique
    @Column(nullable = false, unique = true, length = 150)
    private String email;

    // Téléphone (optionnel)
    @Column(length = 30)
    private String phone;

    // Adresse complète (simple pour l’instant)
    @Column(length = 255)
    private String address;

    // Actif / inactif
    @Column(nullable = false)
    private boolean active;

    // Timestamps
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
        if (!this.active) {
            this.active = true; // par défaut, un customer est actif
        }
    }

    @PreUpdate
    void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
