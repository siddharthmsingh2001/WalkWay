package com.walkway.inventory_service.dto.inventory_warehouse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class InventoryWarehouseDto {
    private Integer warehouseId;
    private String warehouseName;
    private Integer locationId;
    private LocalDateTime warehouseCreatedAt;
    private LocalDateTime warehouseUpdatedAt;
}
