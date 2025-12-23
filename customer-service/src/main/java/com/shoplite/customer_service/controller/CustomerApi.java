package com.shoplite.customer_service.controller;

import com.shoplite.customer_service.dto.CustomerRequestDto;
import com.shoplite.customer_service.dto.CustomerResponseDto;
import com.shoplite.customer_service.error.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Customers")
@RequestMapping("/api/v1/customers")
public interface CustomerApi {

    @Operation(summary = "Lister tous les clients")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = CustomerResponseDto.class)))),
            @ApiResponse(responseCode = "500", description = "Erreur serveur",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping
    ResponseEntity<List<CustomerResponseDto>> getAllCustomers();

    @Operation(summary = "Récupérer un client par ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomerResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Client non trouvé",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Erreur serveur",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{id}")
    ResponseEntity<CustomerResponseDto> getCustomer(
            @Parameter(description = "ID du client", example = "1")
            @PathVariable Long id
    );

    @Operation(summary = "Créer un client")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Client créé",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomerResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Erreur de validation",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Erreur serveur",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping
    ResponseEntity<CustomerResponseDto> createCustomer(
            @Valid @RequestBody CustomerRequestDto request
    );

    @Operation(summary = "Modifier un client")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Client modifié",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomerResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Erreur de validation",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Client non trouvé",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Erreur serveur",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PutMapping("/{id}")
    ResponseEntity<CustomerResponseDto> update(
            @Parameter(description = "ID du client", example = "1")
            @PathVariable Long id,
            @Valid @RequestBody CustomerRequestDto dto
    );

    @Operation(summary = "Supprimer un client")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Client supprimé", content = @Content),
            @ApiResponse(responseCode = "404", description = "Client non trouvé",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Erreur serveur",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(
            @Parameter(description = "ID du client", example = "1")
            @PathVariable Long id
    );

    @Operation(summary = "Vérifier si un client existe")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Boolean.class))),
            @ApiResponse(responseCode = "500", description = "Erreur serveur",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{id}/exists")
    ResponseEntity<Boolean> customerExists(
            @Parameter(description = "ID du client", example = "1")
            @PathVariable Long id
    );
}
