package com.walkway.inventory_service.dto.inventory_stock;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryStockDto {

    private Integer stockId;
    private Integer warehouseId;
    private Integer productSnapshotId;
    private Integer stockQuantityAvailable;
    private Integer stockQuantityReserved;
    private LocalDateTime stockCreatedAt;
    private LocalDateTime stockUpdatedAt;

}
