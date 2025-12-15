package com.shoplite.productservice.exception;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value          // classe immuable (getters, equals, hashCode, toString)
@Builder        // pattern builder pour la construire facilement
public class ErrorResponse {

    LocalDateTime timestamp;
    int status;          // HTTP status code (404, 400, 500...)
    String error;        // code d'erreur technique : PRODUCT_NOT_FOUND, SKU_ALREADY_EXISTS...
    String message;      // message lisible pour l'utilisateur / le client API
    String path;         // l’URL de la requête (/api/v1/products/99)
}
