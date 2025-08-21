package com.walkway.inventory_service.controller;

import com.walkway.inventory_service.dto.inventory_location.InventoryLocationCreateDto;
import com.walkway.inventory_service.dto.inventory_location.InventoryLocationDto;
import com.walkway.inventory_service.dto.inventory_location.InventoryLocationUpdateDto;
import com.walkway.inventory_service.dto.inventory_product_snapshot.InventoryProductSnapshotCreateDto;
import com.walkway.inventory_service.dto.inventory_product_snapshot.InventoryProductSnapshotDto;
import com.walkway.inventory_service.dto.inventory_product_snapshot.InventoryProductSnapshotUpdateDto;
import com.walkway.inventory_service.exception.inventory_location.InventoryLocationNotFoundException;
import com.walkway.inventory_service.exception.inventory_product_snapshot.InventoryProductSnapshotAlreadyExistsException;
import com.walkway.inventory_service.exception.inventory_product_snapshot.InventoryProductSnapshotNotFoundException;
import com.walkway.inventory_service.service.InventoryLocationService;
import com.walkway.inventory_service.service.InventoryProductSnapshotService;
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
        name ="Inventory Product Snapshot Read Update Delete Operations",
        description = "Inventory Product Snapshot Read Update and Delete Operations can be performed here"
)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/inventory")
public class InventoryProductSnapshotController {

    private final InventoryProductSnapshotService inventoryProductSnapshotService;

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "HTTP Status CREATED"),
                    @ApiResponse(responseCode = "409", description = "HTTP Status CONFLICT")
            }
    )
    @Operation(
            summary = "Create Inventory Product Snapshot",
            description = "Admin can Create the Product Snapshot associated to the Inventory",
            security = @SecurityRequirement(name = "inventory-service-pkce"))
    @PostMapping("/product-snapshot")
    public ResponseEntity<InventoryProductSnapshotDto> createProductSnapshot(
            @RequestBody @Valid InventoryProductSnapshotCreateDto inventoryProductSnapshotCreateDto
            ) throws InventoryProductSnapshotAlreadyExistsException {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(inventoryProductSnapshotService.createInventoryProductSnapshot(inventoryProductSnapshotCreateDto));
    }

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "HTTP Status OK")
            }
    )
    @Operation(
            summary = "Read Inventory Product Snapshot",
            description = "Users can Read all the Product Snapshot associated to the Inventory",
            security = @SecurityRequirement(name = "inventory-service-pkce"))
    @GetMapping("/product-snapshot")
    public ResponseEntity<List<InventoryProductSnapshotDto>> readProductSnapshot(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(inventoryProductSnapshotService.getInventoryProductSnapshot());
    }

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
                    @ApiResponse(responseCode = "404", description = "HTTP Status NOT FOUND")
            }
    )
    @Operation(
            summary = "Read Inventory Product Snapshot",
            description = "Users can Read the Product Snapshot by id, associated to the Inventory",
            security = @SecurityRequirement(name = "inventory-service-pkce"))
    @GetMapping("/product-snapshot/{productSnapshotId}")
    public ResponseEntity<InventoryProductSnapshotDto> readProductSnapshot(
            @PathVariable Integer productSnapshotId
    ) throws InventoryProductSnapshotNotFoundException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(inventoryProductSnapshotService.getInventoryProductSnapshot(productSnapshotId));
    }

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
                    @ApiResponse(responseCode = "404", description = "HTTP Status NOT FOUND"),
                    @ApiResponse(responseCode = "409", description = "HTTP Status CONFLICT")
            }
    )
    @Operation(
            summary = "Update Inventory Product Snapshot",
            description = "Admin can Update the Product Snapshot associated to the Inventory",
            security = @SecurityRequirement(name = "inventory-service-pkce"))
    @PutMapping("/product-snapshot/{productSnapshotId}")
    public ResponseEntity<InventoryProductSnapshotDto> updateProductSnapshot(
            @PathVariable Integer productSnapshotId,
            @RequestBody @Valid InventoryProductSnapshotUpdateDto inventoryProductSnapshotUpdateDto
            ) throws InventoryProductSnapshotAlreadyExistsException, InventoryProductSnapshotNotFoundException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(inventoryProductSnapshotService.updateInventoryProductSnapshot(inventoryProductSnapshotUpdateDto, productSnapshotId));
    }

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "204", description = "HTTP Status NO CONTENT"),
                    @ApiResponse(responseCode = "404", description = "HTTP Status NOT FOUND")
            }
    )
    @Operation(
            summary = "Delete Inventory Product Snapshot",
            description = "Admin can Delete the Product Snapshot associated to the Inventory",
            security = @SecurityRequirement(name = "inventory-service-pkce"))
    @DeleteMapping("/product-snapshot/{productSnapshotId}")
    public ResponseEntity<Void> deleteLocation(
            @PathVariable Integer productSnapshotId
    ) throws InventoryProductSnapshotNotFoundException {
        inventoryProductSnapshotService.deleteInventoryProductSnapshot(productSnapshotId);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
