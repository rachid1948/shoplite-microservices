package com.shoplite.order_service.enums;

public enum OrderStatus {
    PENDING,        // commande créée mais pas encore payée
    PAID,           // paiement OK
    SHIPPED,        // expédiée
    DELIVERED,      // livrée
    CANCELLED       // annulée
}
