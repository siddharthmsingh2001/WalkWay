package com.walkway.inventory_service.controller;

import com.walkway.inventory_service.dto.inventory_location.InventoryLocationCreateDto;
import com.walkway.inventory_service.dto.inventory_location.InventoryLocationDto;
import com.walkway.inventory_service.dto.inventory_location.InventoryLocationUpdateDto;
import com.walkway.inventory_service.exception.inventory_location.InventoryLocationNotFoundException;
import com.walkway.inventory_service.service.InventoryLocationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(
        name ="Inventory Location Read Update Delete Operations",
        description = "Inventory Location Read Update and Delete Operations can be performed here"
)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/inventory")
public class InventoryLocationController {

    private final InventoryLocationService inventoryLocationService;

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "HTTP Status CREATED")
            }
    )
    @Operation(
            summary = "Create Inventory Location",
            description = "Admin can Create the Location associated to the Inventory",
            security = @SecurityRequirement(name = "inventory-service-pkce"))
    @PostMapping("/location")
    public ResponseEntity<InventoryLocationDto> createLocation(
            @RequestBody @Valid InventoryLocationCreateDto inventoryLocationCreateDto
    ){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(inventoryLocationService.createInventoryLocation(inventoryLocationCreateDto));
    }

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "HTTP Status OK")
            }
    )
    @Operation(
            summary = "Read Inventory Location",
            description = "Users can Read all the Location associated to the Inventory",
            security = @SecurityRequirement(name = "inventory-service-pkce"))
    @GetMapping("/location")
    public ResponseEntity<List<InventoryLocationDto>> readLocation(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(inventoryLocationService.getInventoryLocation());
    }

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
                    @ApiResponse(responseCode = "404", description = "HTTP Status NOT FOUND")
            }
    )
    @Operation(
            summary = "Read Inventory Location",
            description = "Users can Read the Location by id, associated to the Inventory",
            security = @SecurityRequirement(name = "inventory-service-pkce"))
    @GetMapping("/location/{locationId}")
    public ResponseEntity<InventoryLocationDto> readLocation(
            @PathVariable Integer locationId
    ) throws InventoryLocationNotFoundException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(inventoryLocationService.getInventoryLocation(locationId));
    }

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
                    @ApiResponse(responseCode = "404", description = "HTTP Status NOT FOUND")
            }
    )
    @Operation(
            summary = "Update Inventory Location",
            description = "Admin can Update the Location associated to the Inventory",
            security = @SecurityRequirement(name = "inventory-service-pkce"))
    @PutMapping("/location/{locationId}")
    public ResponseEntity<InventoryLocationDto> updateLocation(
            @PathVariable Integer locationId,
            @RequestBody @Valid InventoryLocationUpdateDto inventoryLocationUpdateDto
    ) throws InventoryLocationNotFoundException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(inventoryLocationService.updateInventoryLocation(inventoryLocationUpdateDto, locationId));
    }

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "204", description = "HTTP Status NO CONTENT"),
                    @ApiResponse(responseCode = "404", description = "HTTP Status NOT FOUND")
            }
    )
    @Operation(
            summary = "Delete Inventory Location",
            description = "Admin can Delete the Location associated to the Inventory",
            security = @SecurityRequirement(name = "inventory-service-pkce"))
    @DeleteMapping("/location/{locationId}")
    public ResponseEntity<Void> deleteLocation(
            @PathVariable Integer locationId
    ) throws InventoryLocationNotFoundException {
        inventoryLocationService.deleteInventoryLocation(locationId);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
