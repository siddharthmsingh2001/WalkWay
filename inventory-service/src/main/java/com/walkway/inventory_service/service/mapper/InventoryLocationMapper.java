package com.walkway.inventory_service.service.mapper;

import com.walkway.inventory_service.dto.inventory_location.InventoryLocationCreateDto;
import com.walkway.inventory_service.dto.inventory_location.InventoryLocationDto;
import com.walkway.inventory_service.dto.inventory_location.InventoryLocationUpdateDto;
import com.walkway.inventory_service.entity.InventoryLocation;
import org.springframework.stereotype.Service;

@Service
public class InventoryLocationMapper {

    public InventoryLocation toInventoryLocation(InventoryLocationCreateDto inventoryLocationCreateDto){
        return InventoryLocation.builder()
                .locationAddress(inventoryLocationCreateDto.getLocationAddress())
                .locationCity(inventoryLocationCreateDto.getLocationCity())
                .locationPostal(inventoryLocationCreateDto.getLocationPostal())
                .locationState(inventoryLocationCreateDto.getLocationState())
                .build();
    }

    public InventoryLocation toInventoryLocation(InventoryLocation inventoryLocation, InventoryLocationUpdateDto inventoryLocationUpdateDto){
        return inventoryLocation
                .setLocationAddress(inventoryLocationUpdateDto.getLocationAddress())
                .setLocationCity(inventoryLocationUpdateDto.getLocationCity())
                .setLocationPostal(inventoryLocationUpdateDto.getLocationPostal())
                .setLocationState(inventoryLocationUpdateDto.getLocationState());
    }

    public InventoryLocationDto toInventoryLocationDto(InventoryLocation inventoryLocation){
        return InventoryLocationDto.builder()
                .locationId(inventoryLocation.getLocationId())
                .locationAddress(inventoryLocation.getLocationAddress())
                .locationCity(inventoryLocation.getLocationCity())
                .locationPostal(inventoryLocation.getLocationPostal())
                .locationState(inventoryLocation.getLocationState())
                .build();
    }
}
