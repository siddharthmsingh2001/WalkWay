package com.walkway.inventory_service.service.impl;

import com.walkway.inventory_service.dto.inventory_warehouse.InventoryWarehouseCreateDto;
import com.walkway.inventory_service.dto.inventory_warehouse.InventoryWarehouseDto;
import com.walkway.inventory_service.dto.inventory_warehouse.InventoryWarehouseUpdateDto;
import com.walkway.inventory_service.entity.InventoryLocation;
import com.walkway.inventory_service.entity.InventoryWarehouse;
import com.walkway.inventory_service.exception.inventory_location.InventoryLocationNotFoundException;
import com.walkway.inventory_service.exception.inventory_warehouse.InventoryWarehouseAlreadyExistsException;
import com.walkway.inventory_service.exception.inventory_warehouse.InventoryWarehouseNotFoundException;
import com.walkway.inventory_service.repo.InventoryLocationRepository;
import com.walkway.inventory_service.repo.InventoryWarehouseRepository;
import com.walkway.inventory_service.service.InventoryWarehouseService;
import com.walkway.inventory_service.service.mapper.InventoryWarehouseMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryWarehouseServiceImpl implements InventoryWarehouseService {

    private final InventoryWarehouseRepository inventoryWarehouseRepository;
    private final InventoryLocationRepository inventoryLocationRepository;
    private final InventoryWarehouseMapper inventoryWarehouseMapper;

    @Override
    @Transactional
    public InventoryWarehouseDto createInventoryWarehouse(InventoryWarehouseCreateDto inventoryWarehouseCreateDto) throws InventoryWarehouseAlreadyExistsException, InventoryLocationNotFoundException {
        if(inventoryWarehouseRepository.existsByWarehouseName(inventoryWarehouseCreateDto.getWarehouseName())){
            throw new InventoryWarehouseAlreadyExistsException();
        }
        InventoryLocation inventoryLocation = inventoryLocationRepository.findById(inventoryWarehouseCreateDto.getLocationId()).orElseThrow(InventoryLocationNotFoundException::new);
        InventoryWarehouse inventoryWarehouse = inventoryWarehouseRepository.save(
                inventoryWarehouseMapper.toInventoryWarehouse(inventoryWarehouseCreateDto,inventoryLocation)
        );
        return inventoryWarehouseMapper.toInventoryWarehouseDto(inventoryWarehouse);
    }

    @Override
    public List<InventoryWarehouseDto> getInventoryWarehouse() {
        return inventoryWarehouseRepository.findAll()
                .stream()
                .map(inventoryWarehouseMapper::toInventoryWarehouseDto)
                .collect(Collectors.toList());
    }

    @Override
    public InventoryWarehouseDto getInventoryWarehouse(Integer warehouseId) throws InventoryWarehouseNotFoundException {
        InventoryWarehouse inventoryWarehouse = inventoryWarehouseRepository.findById(warehouseId).orElseThrow(InventoryWarehouseNotFoundException::new);
        return inventoryWarehouseMapper.toInventoryWarehouseDto(inventoryWarehouse);
    }

    @Override
    public InventoryWarehouseDto getInventoryWarehouse(String warehouseName) throws InventoryWarehouseNotFoundException {
        InventoryWarehouse inventoryWarehouse = inventoryWarehouseRepository.findByWarehouseName(warehouseName).orElseThrow(InventoryWarehouseNotFoundException::new);
        return inventoryWarehouseMapper.toInventoryWarehouseDto(inventoryWarehouse);
    }

    @Override
    @Transactional
    public InventoryWarehouseDto updateInventoryWarehouse(InventoryWarehouseUpdateDto inventoryWarehouseUpdateDto, Integer warehouseId) throws InventoryWarehouseNotFoundException, InventoryWarehouseAlreadyExistsException, InventoryLocationNotFoundException {
        InventoryWarehouse inventoryWarehouse = inventoryWarehouseRepository.findById(warehouseId).orElseThrow(InventoryWarehouseNotFoundException::new);
        if(!inventoryWarehouse.getWarehouseName().equals(inventoryWarehouseUpdateDto.getWarehouseName()) && inventoryWarehouseRepository.existsByWarehouseName(inventoryWarehouseUpdateDto.getWarehouseName())){
            throw new InventoryWarehouseAlreadyExistsException();
        }
        InventoryLocation inventoryLocation = inventoryLocationRepository.findById(inventoryWarehouseUpdateDto.getLocationId()).orElseThrow(InventoryLocationNotFoundException::new);
        return inventoryWarehouseMapper.toInventoryWarehouseDto(
                inventoryWarehouseRepository.save(inventoryWarehouseMapper.toInventoryWarehouse(inventoryWarehouse, inventoryWarehouseUpdateDto, inventoryLocation))
        );
    }

    @Override
    @Transactional
    public void deleteInventoryWarehouse(Integer warehouseId) throws InventoryWarehouseNotFoundException {
        InventoryWarehouse inventoryWarehouse = inventoryWarehouseRepository.findById(warehouseId).orElseThrow(InventoryWarehouseNotFoundException::new);
        inventoryWarehouseRepository.delete(inventoryWarehouse);
    }
}
