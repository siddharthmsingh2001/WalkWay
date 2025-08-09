package com.walkway.inventory_service.service.mapper;

import com.walkway.inventory_service.dto.inventory_warehouse.InventoryWarehouseCreateDto;
import com.walkway.inventory_service.dto.inventory_warehouse.InventoryWarehouseDto;
import com.walkway.inventory_service.dto.inventory_warehouse.InventoryWarehouseUpdateDto;
import com.walkway.inventory_service.entity.InventoryLocation;
import com.walkway.inventory_service.entity.InventoryWarehouse;
import org.springframework.stereotype.Service;

@Service
public class InventoryWarehouseMapper {

    public InventoryWarehouse toInventoryWarehouse(InventoryWarehouseCreateDto inventoryWarehouseCreateDto, InventoryLocation inventoryLocation){
        return InventoryWarehouse.builder()
                .warehouseName(inventoryWarehouseCreateDto.getWarehouseName())
                .warehouseLocation(inventoryLocation)
                .build();
    }

    public InventoryWarehouse toInventoryWarehouse(InventoryWarehouse inventoryWarehouse, InventoryWarehouseUpdateDto inventoryWarehouseUpdateDto, InventoryLocation inventoryLocation){
        return inventoryWarehouse
                .setWarehouseName(inventoryWarehouseUpdateDto.getWarehouseName())
                .setWarehouseLocation(inventoryLocation);
    }

    public InventoryWarehouseDto toInventoryWarehouseDto(InventoryWarehouse inventoryWarehouse){
        return InventoryWarehouseDto.builder()
                .warehouseId(inventoryWarehouse.getWarehouseId())
                .warehouseName(inventoryWarehouse.getWarehouseName())
                .locationId(inventoryWarehouse.getWarehouseLocation().getLocationId())
                .warehouseCreatedAt(inventoryWarehouse.getWarehouseCreatedAt())
                .warehouseUpdatedAt(inventoryWarehouse.getWarehouseUpdatedAt())
                .build();
    }

}
