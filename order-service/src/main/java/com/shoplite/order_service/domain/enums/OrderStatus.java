package com.shoplite.order_service.domain.enums;

public enum OrderStatus {
    PENDING,      // commande créée mais pas encore confirmée
    CONFIRMED,    // paiement validé / commande acceptée
    SHIPPED,      // en cours de livraison
    DELIVERED,    // livrée au client
    CANCELLED     // annulée
}

