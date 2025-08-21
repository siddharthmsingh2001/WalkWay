package com.walkway.inventory_service.service.impl;

import com.walkway.inventory_service.dto.inventory_product_snapshot.InventoryProductSnapshotCreateDto;
import com.walkway.inventory_service.dto.inventory_product_snapshot.InventoryProductSnapshotDto;
import com.walkway.inventory_service.dto.inventory_product_snapshot.InventoryProductSnapshotUpdateDto;
import com.walkway.inventory_service.entity.InventoryProductSnapshot;
import com.walkway.inventory_service.exception.inventory_product_snapshot.InventoryProductSnapshotAlreadyExistsException;
import com.walkway.inventory_service.exception.inventory_product_snapshot.InventoryProductSnapshotNotFoundException;
import com.walkway.inventory_service.repo.InventoryProductSnapshotRepository;
import com.walkway.inventory_service.service.InventoryProductSnapshotService;
import com.walkway.inventory_service.service.mapper.InventoryProductSnapshotMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryProductSnapshotServiceImpl implements InventoryProductSnapshotService {

    private final InventoryProductSnapshotRepository inventoryProductSnapshotRepository;
    private final InventoryProductSnapshotMapper inventoryProductSnapshotMapper;

    @Override
    @Transactional
    public InventoryProductSnapshotDto createInventoryProductSnapshot(InventoryProductSnapshotCreateDto inventoryProductSnapshotCreateDto) throws InventoryProductSnapshotAlreadyExistsException {
        if(inventoryProductSnapshotRepository.existsByProductCode(inventoryProductSnapshotCreateDto.getProductCode())){
            throw new InventoryProductSnapshotAlreadyExistsException();
        }
        InventoryProductSnapshot inventoryProductSnapshot = inventoryProductSnapshotRepository.save(
                inventoryProductSnapshotMapper.toInventoryProductSnapshot(inventoryProductSnapshotCreateDto)
        );
        return inventoryProductSnapshotMapper.toInventoryProductSnapshotDto(inventoryProductSnapshot);
    }

    @Override
    public List<InventoryProductSnapshotDto> getInventoryProductSnapshot() {
        return inventoryProductSnapshotRepository.findAll()
                .stream()
                .map(inventoryProductSnapshotMapper::toInventoryProductSnapshotDto)
                .collect(Collectors.toList());
    }

    @Override
    public InventoryProductSnapshotDto getInventoryProductSnapshot(Integer productCode) throws InventoryProductSnapshotNotFoundException {
        InventoryProductSnapshot inventoryProductSnapshot = inventoryProductSnapshotRepository.findByProductCode(productCode).orElseThrow(InventoryProductSnapshotNotFoundException::new);
        return inventoryProductSnapshotMapper.toInventoryProductSnapshotDto(inventoryProductSnapshot);
    }

    @Override
    @Transactional
    public InventoryProductSnapshotDto updateInventoryProductSnapshot(InventoryProductSnapshotUpdateDto inventoryProductSnapshotUpdateDto, Integer productSnapshotId) throws InventoryProductSnapshotNotFoundException, InventoryProductSnapshotAlreadyExistsException {
        InventoryProductSnapshot inventoryProductSnapshot = inventoryProductSnapshotRepository.findById(productSnapshotId).orElseThrow(InventoryProductSnapshotNotFoundException::new);
        if(inventoryProductSnapshotRepository.existsByProductCode(inventoryProductSnapshotUpdateDto.getProductCode())){
            throw new InventoryProductSnapshotAlreadyExistsException();
        }
        return inventoryProductSnapshotMapper.toInventoryProductSnapshotDto(
                inventoryProductSnapshotRepository.save(inventoryProductSnapshotMapper.toInventoryProductSnapshot(inventoryProductSnapshot,inventoryProductSnapshotUpdateDto))
        );
    }

    @Override
    @Transactional
    public void deleteInventoryProductSnapshot(Integer productSnapshotId) throws InventoryProductSnapshotNotFoundException {
        InventoryProductSnapshot inventoryProductSnapshot = inventoryProductSnapshotRepository.findById(productSnapshotId).orElseThrow(InventoryProductSnapshotNotFoundException::new);
        inventoryProductSnapshotRepository.delete(inventoryProductSnapshot);
    }

    @Override
    @Transactional
    public void deleteInventoryProductSnapshotByProductCode(Integer productCode) throws InventoryProductSnapshotNotFoundException{
        InventoryProductSnapshot inventoryProductSnapshot = inventoryProductSnapshotRepository.findByProductCode(productCode).orElseThrow(InventoryProductSnapshotNotFoundException::new);
        inventoryProductSnapshotRepository.delete(inventoryProductSnapshot);
    }
}
