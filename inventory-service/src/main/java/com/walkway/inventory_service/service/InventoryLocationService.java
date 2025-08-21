package com.walkway.inventory_service.service;

import com.walkway.inventory_service.dto.inventory_location.InventoryLocationCreateDto;
import com.walkway.inventory_service.dto.inventory_location.InventoryLocationDto;
import com.walkway.inventory_service.dto.inventory_location.InventoryLocationUpdateDto;
import com.walkway.inventory_service.entity.InventoryLocation;
import com.walkway.inventory_service.exception.inventory_location.InventoryLocationNotFoundException;

import java.util.List;

public interface InventoryLocationService {


    InventoryLocationDto createInventoryLocation(InventoryLocationCreateDto inventoryLocationCreateDto);

    List<InventoryLocationDto> getInventoryLocation();

    InventoryLocationDto getInventoryLocation(Integer locationId) throws InventoryLocationNotFoundException;

    InventoryLocationDto updateInventoryLocation(InventoryLocationUpdateDto inventoryLocationUpdateDto, Integer locationId) throws InventoryLocationNotFoundException;

    void deleteInventoryLocation(Integer locationId) throws InventoryLocationNotFoundException;
}
