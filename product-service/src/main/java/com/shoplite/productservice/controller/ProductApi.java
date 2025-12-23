package com.shoplite.productservice.controller;

import com.shoplite.productservice.dto.ProductRequestDto;
import com.shoplite.productservice.dto.ProductResponseDto;
import com.shoplite.productservice.exception.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Productss")
@RequestMapping("/api/v1/products")
public interface ProductApi {

    // --- PING ---
    @Operation(summary = "Ping du service", description = "Vérifie que product-service tourne")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "text/plain",
                            schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Erreur serveur",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/ping")
    ResponseEntity<String> ping();

    // --- CREATE ---
    @Operation(summary = "Créer un produit")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Produit créé",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "SKU déjà existant / Erreur de validation",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Erreur serveur",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping
    ResponseEntity<ProductResponseDto> createProduct(
            @Valid @org.springframework.web.bind.annotation.RequestBody ProductRequestDto requestDto
    );

    // --- UPDATE ---
    @Operation(summary = "Modifier un produit")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Produit modifié",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Erreur de validation / SKU déjà existant",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Produit non trouvé",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Erreur serveur",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PutMapping("/{id}")
    ResponseEntity<ProductResponseDto> updateProduct(
            @Parameter(description = "ID du produit", example = "1")
            @PathVariable Long id,
            @Valid @org.springframework.web.bind.annotation.RequestBody ProductRequestDto requestDto
    );

    // --- DELETE ---
    @Operation(summary = "Supprimer un produit")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Produit supprimé", content = @Content),
            @ApiResponse(responseCode = "404", description = "Produit non trouvé",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Erreur serveur",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteProduct(
            @Parameter(description = "ID du produit", example = "1")
            @PathVariable Long id
    );

    // --- GET BY ID ---
    @Operation(summary = "Récupérer un produit par ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Produit non trouvé",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Erreur serveur",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{id}")
    ResponseEntity<ProductResponseDto> getProductById(
            @Parameter(description = "ID du produit", example = "1")
            @PathVariable Long id
    );

    // --- GET BY SKU ---
    @Operation(summary = "Récupérer un produit par SKU")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Produit non trouvé",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Erreur serveur",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/sku/{sku}")
    ResponseEntity<ProductResponseDto> getProductBySku(
            @Parameter(description = "SKU du produit", example = "SKU-001")
            @PathVariable String sku
    );

    // --- GET ALL ---
    @Operation(summary = "Lister tous les produits")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ProductResponseDto.class)))),
            @ApiResponse(responseCode = "500", description = "Erreur serveur",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping
    ResponseEntity<List<ProductResponseDto>> getAllProducts();
}
