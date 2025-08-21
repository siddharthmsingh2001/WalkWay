package com.walkway.inventory_service.service;

import com.walkway.inventory_service.dto.inventory_stock.InventoryStockCreateDto;
import com.walkway.inventory_service.dto.inventory_stock.InventoryStockDto;
import com.walkway.inventory_service.dto.inventory_stock.InventoryStockUpdateDto;
import com.walkway.inventory_service.exception.inventory_product_snapshot.InventoryProductSnapshotNotFoundException;
import com.walkway.inventory_service.exception.inventory_stock.InventoryStockAlreadyExistsException;
import com.walkway.inventory_service.exception.inventory_stock.InventoryStockNotFoundException;
import com.walkway.inventory_service.exception.inventory_warehouse.InventoryWarehouseNotFoundException;

import java.util.List;

public interface InventoryStockService {

    InventoryStockDto createInventoryStock(InventoryStockCreateDto inventoryStockCreateDto) throws InventoryWarehouseNotFoundException, InventoryStockAlreadyExistsException, InventoryProductSnapshotNotFoundException;

    List<InventoryStockDto> getInventoryStock();

    InventoryStockDto getInventoryStock(Integer stockId) throws InventoryStockNotFoundException;

    List<InventoryStockDto> getInventoryStockByProductSnapshot(Integer productSnapshotId) throws InventoryProductSnapshotNotFoundException;

    List<InventoryStockDto> getInventoryStockByWarehouse(Integer warehouseId) throws InventoryWarehouseNotFoundException;

    InventoryStockDto getInventoryStock(Integer productSnapshotId, Integer warehouseId) throws InventoryProductSnapshotNotFoundException, InventoryWarehouseNotFoundException, InventoryStockNotFoundException;

    InventoryStockDto updateInventoryStock(InventoryStockUpdateDto inventoryStockUpdateDto, Integer stockId) throws InventoryStockNotFoundException, InventoryWarehouseNotFoundException, InventoryProductSnapshotNotFoundException, InventoryStockAlreadyExistsException;

    InventoryStockDto addAvailableStock(Integer stockId, Integer quantityChange) throws InventoryStockNotFoundException;

    InventoryStockDto deductAvailableStock(Integer stockId, Integer quantityChange) throws InventoryStockNotFoundException;

    void deleteInventoryStock(Integer stockId) throws InventoryStockNotFoundException;
}
