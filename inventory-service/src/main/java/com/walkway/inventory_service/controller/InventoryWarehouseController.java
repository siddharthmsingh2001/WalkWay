package com.walkway.inventory_service.controller;

import com.walkway.inventory_service.dto.inventory_warehouse.InventoryWarehouseCreateDto;
import com.walkway.inventory_service.dto.inventory_warehouse.InventoryWarehouseDto;
import com.walkway.inventory_service.dto.inventory_warehouse.InventoryWarehouseUpdateDto;
import com.walkway.inventory_service.exception.inventory_location.InventoryLocationNotFoundException;
import com.walkway.inventory_service.exception.inventory_warehouse.InventoryWarehouseAlreadyExistsException;
import com.walkway.inventory_service.exception.inventory_warehouse.InventoryWarehouseNotFoundException;
import com.walkway.inventory_service.service.InventoryWarehouseService;
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
        name ="Inventory Warehouse Read Update Delete Operations",
        description = "Inventory Warehouse Read Update and Delete Operations can be performed here"
)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/inventory")
public class InventoryWarehouseController {

    private final InventoryWarehouseService inventoryWarehouseService;

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "HTTP Status CREATED"),
                    @ApiResponse(responseCode = "404", description = "HTTP Status NOT FOUND"),
                    @ApiResponse(responseCode = "409", description = "HTTP Status CONFLICT")
            }
    )
    @Operation(
            summary = "Create Inventory Warehouse",
            description = "Admin can Create the Warehouse associated to the Inventory",
            security = @SecurityRequirement(name = "inventory-service-pkce"))
    @PostMapping("/warehouse")
    public ResponseEntity<InventoryWarehouseDto> createWarehouse(
            @RequestBody @Valid InventoryWarehouseCreateDto inventoryWarehouseCreateDto
    ) throws InventoryWarehouseAlreadyExistsException, InventoryLocationNotFoundException {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(inventoryWarehouseService.createInventoryWarehouse(inventoryWarehouseCreateDto));
    }

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "HTTP Status OK")
            }
    )
    @Operation(
            summary = "Read Inventory Warehouse",
            description = "Users can Read all the Warehouse associated to the Inventory",
            security = @SecurityRequirement(name = "inventory-service-pkce"))
    @GetMapping("/warehouse")
    public ResponseEntity<List<InventoryWarehouseDto>> readWarehouse(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(inventoryWarehouseService.getInventoryWarehouse());
    }

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
                    @ApiResponse(responseCode = "404", description = "HTTP Status NOT FOUND")
            }
    )
    @Operation(
            summary = "Read Inventory Warehouse",
            description = "Users can Read the Warehouse by id, associated to the Inventory",
            security = @SecurityRequirement(name = "inventory-service-pkce"))
    @GetMapping("/warehouse/{warehouseId}")
    public ResponseEntity<InventoryWarehouseDto> readWarehouse(
            @PathVariable Integer warehouseId
    ) throws InventoryWarehouseNotFoundException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(inventoryWarehouseService.getInventoryWarehouse(warehouseId));
    }

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
                    @ApiResponse(responseCode = "404", description = "HTTP Status NOT FOUND"),
                    @ApiResponse(responseCode = "409", description = "HTTP Status CONFLICT")
            }
    )
    @Operation(
            summary = "Update Inventory Warehouse",
            description = "Admin can Update the Warehouse associated to the Inventory",
            security = @SecurityRequirement(name = "inventory-service-pkce"))
    @PutMapping("/warehouse/{warehouseId}")
    public ResponseEntity<InventoryWarehouseDto> updateWarehouse(
            @PathVariable Integer warehouseId,
            @RequestBody @Valid InventoryWarehouseUpdateDto inventoryWarehouseUpdateDto
    ) throws InventoryWarehouseAlreadyExistsException, InventoryLocationNotFoundException, InventoryWarehouseNotFoundException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(inventoryWarehouseService.updateInventoryWarehouse(inventoryWarehouseUpdateDto, warehouseId));
    }

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "204", description = "HTTP Status NO CONTENT"),
                    @ApiResponse(responseCode = "404", description = "HTTP Status NOT FOUND")
            }
    )
    @Operation(
            summary = "Delete Inventory Warehouse",
            description = "Admin can Delete the Warehouse associated to the Inventory",
            security = @SecurityRequirement(name = "inventory-service-pkce"))
    @DeleteMapping("/warehouse/{warehouseId}")
    public ResponseEntity<Void> deleteWarehouse(
            @PathVariable Integer warehouseId
    ) throws InventoryWarehouseNotFoundException {
        inventoryWarehouseService.deleteInventoryWarehouse(warehouseId);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
