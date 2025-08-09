package com.walkway.inventory_service.service.mapper;

import com.walkway.inventory_service.dto.inventory_stock.InventoryStockCreateDto;
import com.walkway.inventory_service.dto.inventory_stock.InventoryStockDto;
import com.walkway.inventory_service.dto.inventory_stock.InventoryStockUpdateDto;
import com.walkway.inventory_service.entity.InventoryProductSnapshot;
import com.walkway.inventory_service.entity.InventoryStock;
import com.walkway.inventory_service.entity.InventoryWarehouse;
import org.springframework.stereotype.Service;

@Service
public class InventoryStockMapper {

    public InventoryStock toInventoryStock(InventoryStockCreateDto inventoryStockCreateDto, InventoryWarehouse inventoryWarehouse, InventoryProductSnapshot inventoryProductSnapshot){
        return InventoryStock.builder()
                .warehouse(inventoryWarehouse)
                .productSnapshot(inventoryProductSnapshot)
                .stockQuantityReserved(inventoryStockCreateDto.getStockQuantityReserved())
                .stockQuantityAvailable(inventoryStockCreateDto.getStockQuantityAvailable())
                .build();
    }

    public InventoryStock toInventoryStock(InventoryStockUpdateDto inventoryStockUpdateDto, InventoryStock inventoryStock, InventoryWarehouse inventoryWarehouse, InventoryProductSnapshot inventoryProductSnapshot){
        return inventoryStock
                .setWarehouse(inventoryWarehouse)
                .setProductSnapshot(inventoryProductSnapshot)
                .setStockQuantityReserved(inventoryStockUpdateDto.getStockQuantityReserved())
                .setStockQuantityAvailable(inventoryStockUpdateDto.getStockQuantityAvailable());
    }

    public InventoryStockDto toInventoryStockDto(InventoryStock inventoryStock){
        return InventoryStockDto.builder()
                .stockId(inventoryStock.getStockId())
                .warehouseId(inventoryStock.getWarehouse().getWarehouseId())
                .productSnapshotId(inventoryStock.getProductSnapshot().getProductSnapshotId())
                .stockQuantityReserved(inventoryStock.getStockQuantityReserved())
                .stockQuantityAvailable(inventoryStock.getStockQuantityAvailable())
                .stockCreatedAt(inventoryStock.getStockCreatedAt())
                .stockUpdatedAt(inventoryStock.getStockUpdatedAt())
                .build();
    }
}
