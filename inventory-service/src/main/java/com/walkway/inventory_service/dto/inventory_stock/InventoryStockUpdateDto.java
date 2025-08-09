package com.walkway.inventory_service.dto.inventory_stock;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryStockUpdateDto {

    @NotNull(message = "Warehouse Id is essential")
    private Integer warehouseId;

    @NotNull(message = "Product Snapshot Id is essential")
    private Integer productSnapshotId;

    @NotNull(message = "Stock Quantity for a given product cannot be null")
    private Integer stockQuantityAvailable;

    @NotNull(message = "Reserved Stock Quantity cannot be null")
    private Integer stockQuantityReserved;

}