package com.walkway.inventory_service.service.impl;

import com.walkway.inventory_service.dto.inventory_location.InventoryLocationCreateDto;
import com.walkway.inventory_service.dto.inventory_location.InventoryLocationDto;
import com.walkway.inventory_service.dto.inventory_location.InventoryLocationUpdateDto;
import com.walkway.inventory_service.entity.InventoryLocation;
import com.walkway.inventory_service.exception.inventory_location.InventoryLocationNotFoundException;
import com.walkway.inventory_service.repo.InventoryLocationRepository;
import com.walkway.inventory_service.service.InventoryLocationService;
import com.walkway.inventory_service.service.mapper.InventoryLocationMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryLocationServiceImpl implements InventoryLocationService {

    private final InventoryLocationMapper inventoryLocationMapper;
    private final InventoryLocationRepository inventoryLocationRepository;

    @Override
    @Transactional
    public InventoryLocationDto createInventoryLocation(InventoryLocationCreateDto inventoryLocationCreateDto) {
        InventoryLocation inventoryLocation = inventoryLocationRepository.save(
                inventoryLocationMapper.toInventoryLocation(inventoryLocationCreateDto)
        );
        return inventoryLocationMapper.toInventoryLocationDto(inventoryLocation);
    }

    @Override
    public List<InventoryLocationDto> getInventoryLocation() {
        return inventoryLocationRepository.findAll()
                .stream()
                .map(inventoryLocationMapper::toInventoryLocationDto)
                .collect(Collectors.toList());
    }

    @Override
    public InventoryLocationDto getInventoryLocation(Integer locationId) throws InventoryLocationNotFoundException {
        InventoryLocation inventoryLocation = inventoryLocationRepository.findById(locationId).orElseThrow(InventoryLocationNotFoundException::new);
        return inventoryLocationMapper.toInventoryLocationDto(inventoryLocation);
    }

    @Override
    @Transactional
    public InventoryLocationDto updateInventoryLocation(InventoryLocationUpdateDto inventoryLocationUpdateDto, Integer locationId) throws InventoryLocationNotFoundException {
        InventoryLocation inventoryLocation = inventoryLocationRepository.findById(locationId).orElseThrow(InventoryLocationNotFoundException::new);
        return inventoryLocationMapper.toInventoryLocationDto(
                inventoryLocationRepository.save(inventoryLocationMapper.toInventoryLocation(inventoryLocation,
                        inventoryLocationUpdateDto))
        );
    }

    @Override
    @Transactional
    public void deleteInventoryLocation(Integer locationId) throws InventoryLocationNotFoundException {
        InventoryLocation inventoryLocation = inventoryLocationRepository.findById(locationId).orElseThrow(InventoryLocationNotFoundException::new);
        inventoryLocationRepository.delete(inventoryLocation);
    }
}
