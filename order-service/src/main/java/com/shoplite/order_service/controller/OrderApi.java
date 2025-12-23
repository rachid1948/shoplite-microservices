package com.shoplite.order_service.api;

import com.shoplite.order_service.domain.enums.OrderStatus;
import com.shoplite.order_service.dto.request.OrderCreateRequestDto;
import com.shoplite.order_service.dto.request.OrderUpdateRequestDto;
import com.shoplite.order_service.dto.request.UpdateOrderStatusRequestDto;
import com.shoplite.order_service.dto.response.OrderResponseDto;
import com.shoplite.order_service.exception.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Orders")
@RequestMapping("/api/v1/orders")
public interface OrderApi {

    // --- TEST PRODUCT (debug) ---
    @Operation(summary = "Tester l'appel Product-service", description = "Endpoint de test (debug) pour récupérer un produit via ProductClient")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK (réponse du product-service)",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Object.class))),
            @ApiResponse(responseCode = "500", description = "Erreur serveur",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/product/{id}")
    ResponseEntity<Object> testProduct(
            @Parameter(description = "ID du produit", example = "1")
            @PathVariable Long id
    );

    // --- CREATE ORDER ---
    @Operation(summary = "Créer une commande")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Commande créée",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrderResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Erreur de validation",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Erreur serveur",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping
    ResponseEntity<OrderResponseDto> create(
            @Valid @RequestBody OrderCreateRequestDto dto
    );

    // --- GET BY ID ---
    @Operation(summary = "Récupérer une commande par ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrderResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Commande non trouvée",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Erreur serveur",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{id}")
    ResponseEntity<OrderResponseDto> getById(
            @Parameter(description = "ID de la commande", example = "100")
            @PathVariable Long id
    );

    // --- DELETE ---
    @Operation(summary = "Supprimer une commande")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Commande supprimée", content = @Content),
            @ApiResponse(responseCode = "404", description = "Commande non trouvée",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Erreur serveur",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(
            @Parameter(description = "ID de la commande", example = "100")
            @PathVariable Long id
    );

    // --- UPDATE STATUS ---
    @Operation(summary = "Modifier le statut d'une commande")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Statut mis à jour",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrderResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Erreur de validation",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Commande non trouvée",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Erreur serveur",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PatchMapping("/{id}/status")
    ResponseEntity<OrderResponseDto> updateStatus(
            @Parameter(description = "ID de la commande", example = "100")
            @PathVariable Long id,
            @Valid @RequestBody UpdateOrderStatusRequestDto request
    );

    // --- UPDATE ORDER ---
    @Operation(summary = "Modifier une commande")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Commande modifiée",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrderResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Erreur de validation",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Commande non trouvée",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Erreur serveur",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PutMapping("/{id}")
    ResponseEntity<OrderResponseDto> update(
            @Parameter(description = "ID de la commande", example = "100")
            @PathVariable Long id,
            @Valid @RequestBody OrderUpdateRequestDto dto
    );

    // --- SEARCH / LIST ---
    @Operation(summary = "Rechercher des commandes", description = "Recherche paginée par customerId et/ou status")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK (page de commandes)",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Page.class))),
            @ApiResponse(responseCode = "500", description = "Erreur serveur",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping
    ResponseEntity<Page<OrderResponseDto>> search(
            @Parameter(description = "ID du client", example = "10")
            @RequestParam(required = false) Long customerId,

            @Parameter(description = "Statut de la commande", schema = @Schema(implementation = OrderStatus.class))
            @RequestParam(required = false) OrderStatus status,

            @ParameterObject Pageable pageable
    );
}
