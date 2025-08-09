package com.walkway.inventory_service.service;

import com.walkway.inventory_service.dto.inventory_warehouse.InventoryWarehouseCreateDto;
import com.walkway.inventory_service.dto.inventory_warehouse.InventoryWarehouseDto;
import com.walkway.inventory_service.dto.inventory_warehouse.InventoryWarehouseUpdateDto;
import com.walkway.inventory_service.exception.inventory_location.InventoryLocationNotFoundException;
import com.walkway.inventory_service.exception.inventory_warehouse.InventoryWarehouseAlreadyExistsException;
import com.walkway.inventory_service.exception.inventory_warehouse.InventoryWarehouseNotFoundException;

import java.util.List;

public interface InventoryWarehouseService {

    InventoryWarehouseDto createInventoryWarehouse(InventoryWarehouseCreateDto inventoryWarehouseCreateDto) throws InventoryWarehouseAlreadyExistsException, InventoryLocationNotFoundException;

    List<InventoryWarehouseDto> getInventoryWarehouse();

    InventoryWarehouseDto getInventoryWarehouse(Integer warehouseId) throws InventoryWarehouseNotFoundException;

    InventoryWarehouseDto getInventoryWarehouse(String warehouseName) throws InventoryWarehouseNotFoundException;

    InventoryWarehouseDto updateInventoryWarehouse(InventoryWarehouseUpdateDto inventoryWarehouseUpdateDto, Integer warehouseId) throws InventoryWarehouseNotFoundException, InventoryWarehouseAlreadyExistsException, InventoryLocationNotFoundException;

    void deleteInventoryWarehouse(Integer warehouseId) throws InventoryWarehouseNotFoundException;
}
