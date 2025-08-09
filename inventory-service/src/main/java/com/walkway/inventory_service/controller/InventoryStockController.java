package com.walkway.inventory_service.controller;

import com.walkway.inventory_service.dto.inventory_location.InventoryLocationCreateDto;
import com.walkway.inventory_service.dto.inventory_location.InventoryLocationDto;
import com.walkway.inventory_service.dto.inventory_location.InventoryLocationUpdateDto;
import com.walkway.inventory_service.dto.inventory_stock.InventoryStockCreateDto;
import com.walkway.inventory_service.dto.inventory_stock.InventoryStockDto;
import com.walkway.inventory_service.dto.inventory_stock.InventoryStockUpdateDto;
import com.walkway.inventory_service.exception.inventory_location.InventoryLocationNotFoundException;
import com.walkway.inventory_service.exception.inventory_product_snapshot.InventoryProductSnapshotNotFoundException;
import com.walkway.inventory_service.exception.inventory_stock.InventoryStockAlreadyExistsException;
import com.walkway.inventory_service.exception.inventory_stock.InventoryStockNotFoundException;
import com.walkway.inventory_service.exception.inventory_warehouse.InventoryWarehouseNotFoundException;
import com.walkway.inventory_service.service.InventoryLocationService;
import com.walkway.inventory_service.service.InventoryStockService;
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
        name ="Inventory Stock Read Update Delete Operations",
        description = "Inventory Stock Read Update and Delete Operations can be performed here"
)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/inventory")
public class InventoryStockController {

    private final InventoryStockService inventoryStockService;

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "HTTP Status CREATED"),
                    @ApiResponse(responseCode = "404", description = "HTTP Status NOT FOUND"),
                    @ApiResponse(responseCode = "409", description = "HTTP Status CONFLICT")
            }
    )
    @Operation(
            summary = "Create Inventory Stock",
            description = "Admin and Sellers can Create the Stock associated to the Inventory",
            security = @SecurityRequirement(name = "inventory-service-pkce"))
    @PostMapping("/stock")
    public ResponseEntity<InventoryStockDto> createStock(
            @RequestBody @Valid InventoryStockCreateDto inventoryLocationCreateDto
            ) throws InventoryStockAlreadyExistsException, InventoryProductSnapshotNotFoundException, InventoryWarehouseNotFoundException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(inventoryStockService.createInventoryStock(inventoryLocationCreateDto));
    }

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "HTTP Status OK")
            }
    )
    @Operation(
            summary = "Read Inventory Stock",
            description = "Users can Read all the Stock associated to the Inventory",
            security = @SecurityRequirement(name = "inventory-service-pkce"))
    @GetMapping("/stock")
    public ResponseEntity<List<InventoryStockDto>> readStock(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(inventoryStockService.getInventoryStock());
    }

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
                    @ApiResponse(responseCode = "404", description = "HTTP Status NOT FOUND")
            }
    )
    @Operation(
            summary = "Read Inventory Stock",
            description = "Users can Read the Stock by id, associated to the Inventory",
            security = @SecurityRequirement(name = "inventory-service-pkce"))
    @GetMapping("/stock/{stockId}")
    public ResponseEntity<InventoryStockDto> readStock(
            @PathVariable Integer stockId
    ) throws InventoryStockNotFoundException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(inventoryStockService.getInventoryStock(stockId));
    }

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
                    @ApiResponse(responseCode = "404", description = "HTTP Status NOT FOUND")
            }
    )
    @Operation(
            summary = "Read Inventory Stock",
            description = "Users can Read all the Stock by warehouse id, associated to the Inventory",
            security = @SecurityRequirement(name = "inventory-service-pkce"))
    @GetMapping("/stock/warehouse/{warehouseId}")
    public ResponseEntity<List<InventoryStockDto>> readStockByWarehouse(
            @PathVariable Integer warehouseId
    ) throws InventoryWarehouseNotFoundException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(inventoryStockService.getInventoryStockByWarehouse(warehouseId));
    }

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
                    @ApiResponse(responseCode = "404", description = "HTTP Status NOT FOUND")
            }
    )
    @Operation(
            summary = "Read Inventory Stock",
            description = "Users can Read all the Stock by product snapshot id, associated to the Inventory",
            security = @SecurityRequirement(name = "inventory-service-pkce"))
    @GetMapping("/stock/product/{productSnapshotId}")
    public ResponseEntity<List<InventoryStockDto>> readStockByProductSnapshot(
            @PathVariable Integer productSnapshotId
    ) throws InventoryProductSnapshotNotFoundException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(inventoryStockService.getInventoryStockByProductSnapshot(productSnapshotId));
    }

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
                    @ApiResponse(responseCode = "404", description = "HTTP Status NOT FOUND")
            }
    )
    @Operation(
            summary = "Read Inventory Stock",
            description = "Users can Read the Stock by warehouse and product snapshot id, associated to the Inventory",
            security = @SecurityRequirement(name = "inventory-service-pkce"))
    @GetMapping("/stock/product/{productSnapshotId}/warehouse/{warehouseId}")
    public ResponseEntity<InventoryStockDto> readStock(
            @PathVariable Integer productSnapshotId,
            @PathVariable Integer warehouseId
    ) throws InventoryStockNotFoundException, InventoryProductSnapshotNotFoundException, InventoryWarehouseNotFoundException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(inventoryStockService.getInventoryStock(productSnapshotId, warehouseId));
    }

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
                    @ApiResponse(responseCode = "404", description = "HTTP Status NOT FOUND"),
                    @ApiResponse(responseCode = "409", description = "HTTP Status CONFLICT")
            }
    )
    @Operation(
            summary = "Update Inventory Stock",
            description = "Admin and Seller can Update the Stock associated to the Inventory",
            security = @SecurityRequirement(name = "inventory-service-pkce"))
    @PutMapping("/stock/{stockId}")
    public ResponseEntity<InventoryStockDto> updateStock(
            @PathVariable Integer stockId,
            @RequestBody @Valid InventoryStockUpdateDto inventoryStockUpdateDto
    ) throws InventoryStockAlreadyExistsException, InventoryStockNotFoundException, InventoryProductSnapshotNotFoundException, InventoryWarehouseNotFoundException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(inventoryStockService.updateInventoryStock(inventoryStockUpdateDto,stockId));
    }

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
                    @ApiResponse(responseCode = "404", description = "HTTP Status NOT FOUND")
            }
    )
    @Operation(
            summary = "Update Inventory Stock",
            description = "Admin and Seller can Atomically Update a particular Stock associated to the Inventory",
            security = @SecurityRequirement(name = "inventory-service-pkce"))
    @PutMapping("/stock/{stockId}/addStock/{quantityChange}")
    public ResponseEntity<InventoryStockDto> addAvailableStock(
            @PathVariable Integer stockId,
            @PathVariable Integer quantityChange
    ) throws InventoryStockNotFoundException{
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(inventoryStockService.addAvailableStock(stockId,quantityChange));
    }

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
                    @ApiResponse(responseCode = "404", description = "HTTP Status NOT FOUND")
            }
    )
    @Operation(
            summary = "Update Inventory Stock",
            description = "Admin and Seller can Atomically Update a particular Stock associated to the Inventory",
            security = @SecurityRequirement(name = "inventory-service-pkce"))
    @PutMapping("/stock/{stockId}/deductStock/{quantityChange}")
    public ResponseEntity<InventoryStockDto> deductAvailableStock(
            @PathVariable Integer stockId,
            @PathVariable Integer quantityChange
    ) throws InventoryStockNotFoundException{
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(inventoryStockService.deductAvailableStock(stockId,quantityChange));
    }

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "204", description = "HTTP Status NO CONTENT"),
                    @ApiResponse(responseCode = "404", description = "HTTP Status NOT FOUND")
            }
    )
    @Operation(
            summary = "Delete Inventory Stock",
            description = "Admin can Delete the Stock associated to the Inventory",
            security = @SecurityRequirement(name = "inventory-service-pkce"))
    @DeleteMapping("/stock/{stockId}")
    public ResponseEntity<Void> deleteLocation(
            @PathVariable Integer stockId
    ) throws InventoryStockNotFoundException {
        inventoryStockService.deleteInventoryStock(stockId);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
