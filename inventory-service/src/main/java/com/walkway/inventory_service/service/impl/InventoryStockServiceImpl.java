package com.walkway.inventory_service.service.impl;

import com.walkway.inventory_service.dto.inventory_stock.InventoryStockCreateDto;
import com.walkway.inventory_service.dto.inventory_stock.InventoryStockDto;
import com.walkway.inventory_service.dto.inventory_stock.InventoryStockUpdateDto;
import com.walkway.inventory_service.entity.InventoryProductSnapshot;
import com.walkway.inventory_service.entity.InventoryStock;
import com.walkway.inventory_service.entity.InventoryWarehouse;
import com.walkway.inventory_service.exception.inventory_product_snapshot.InventoryProductSnapshotNotFoundException;
import com.walkway.inventory_service.exception.inventory_stock.InventoryStockAlreadyExistsException;
import com.walkway.inventory_service.exception.inventory_stock.InventoryStockNotFoundException;
import com.walkway.inventory_service.exception.inventory_warehouse.InventoryWarehouseNotFoundException;
import com.walkway.inventory_service.repo.InventoryProductSnapshotRepository;
import com.walkway.inventory_service.repo.InventoryStockRepository;
import com.walkway.inventory_service.repo.InventoryWarehouseRepository;
import com.walkway.inventory_service.service.InventoryStockService;
import com.walkway.inventory_service.service.mapper.InventoryStockMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryStockServiceImpl implements InventoryStockService {

    private final InventoryStockMapper inventoryStockMapper;
    private final InventoryStockRepository inventoryStockRepository;
    private final InventoryWarehouseRepository inventoryWarehouseRepository;
    private final InventoryProductSnapshotRepository inventoryProductSnapshotRepository;

    @Override
    @Transactional
    public InventoryStockDto createInventoryStock(InventoryStockCreateDto inventoryStockCreateDto) throws InventoryWarehouseNotFoundException, InventoryStockAlreadyExistsException, InventoryProductSnapshotNotFoundException {
        InventoryWarehouse inventoryWarehouse = inventoryWarehouseRepository.findById(inventoryStockCreateDto.getWarehouseId()).orElseThrow(InventoryWarehouseNotFoundException::new);
        InventoryProductSnapshot inventoryProductSnapshot = inventoryProductSnapshotRepository.findById(inventoryStockCreateDto.getProductSnapshotId()).orElseThrow(InventoryProductSnapshotNotFoundException::new);
        if(inventoryStockRepository.existsByWarehouseAndProductSnapshot(inventoryWarehouse,inventoryProductSnapshot)){
            throw new InventoryStockAlreadyExistsException();
        }
        InventoryStock inventoryStock = inventoryStockRepository.save(
                inventoryStockMapper.toInventoryStock(inventoryStockCreateDto, inventoryWarehouse, inventoryProductSnapshot)
        );
        // In the future, we would also Add this data to Inventory Transaction table
        return inventoryStockMapper.toInventoryStockDto(inventoryStock);
    }

    @Override
    public List<InventoryStockDto> getInventoryStock() {
        return inventoryStockRepository.findAll()
                .stream()
                .map(inventoryStockMapper::toInventoryStockDto)
                .collect(Collectors.toList());
    }

    @Override
    public InventoryStockDto getInventoryStock(Integer stockId) throws InventoryStockNotFoundException {
        InventoryStock inventoryStock = inventoryStockRepository.findById(stockId).orElseThrow(InventoryStockNotFoundException::new);
        return inventoryStockMapper.toInventoryStockDto(inventoryStock);
    }

    @Override
    public List<InventoryStockDto> getInventoryStockByProductSnapshot(Integer productSnapshotId) throws InventoryProductSnapshotNotFoundException {
        InventoryProductSnapshot inventoryProductSnapshot = inventoryProductSnapshotRepository.findById(productSnapshotId).orElseThrow(InventoryProductSnapshotNotFoundException::new);
        return inventoryStockRepository.findByProductSnapshot(inventoryProductSnapshot)
                .stream()
                .map(inventoryStockMapper::toInventoryStockDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<InventoryStockDto> getInventoryStockByWarehouse(Integer warehouseId) throws InventoryWarehouseNotFoundException {
        InventoryWarehouse inventoryWarehouse = inventoryWarehouseRepository.findById(warehouseId).orElseThrow(InventoryWarehouseNotFoundException::new);
        return inventoryStockRepository.findByWarehouse(inventoryWarehouse)
                .stream()
                .map(inventoryStockMapper::toInventoryStockDto)
                .collect(Collectors.toList());
    }

    @Override
    public InventoryStockDto getInventoryStock(Integer productSnapshotId, Integer warehouseId) throws InventoryProductSnapshotNotFoundException, InventoryWarehouseNotFoundException, InventoryStockNotFoundException {
        InventoryProductSnapshot inventoryProductSnapshot = inventoryProductSnapshotRepository.findById(productSnapshotId).orElseThrow(InventoryProductSnapshotNotFoundException::new);
        InventoryWarehouse inventoryWarehouse = inventoryWarehouseRepository.findById(warehouseId).orElseThrow(InventoryWarehouseNotFoundException::new);
        InventoryStock inventoryStock = inventoryStockRepository.findByWarehouseAndProductSnapshot(inventoryWarehouse,inventoryProductSnapshot).orElseThrow(InventoryStockNotFoundException::new);
        return inventoryStockMapper.toInventoryStockDto(inventoryStock);
    }

    @Override
    @Transactional
    public InventoryStockDto updateInventoryStock(InventoryStockUpdateDto inventoryStockUpdateDto, Integer stockId) throws InventoryStockNotFoundException, InventoryWarehouseNotFoundException, InventoryProductSnapshotNotFoundException, InventoryStockAlreadyExistsException {

        InventoryStock inventoryStock = inventoryStockRepository.findById(stockId).orElseThrow(InventoryStockNotFoundException::new);

        InventoryWarehouse inventoryWarehouse = inventoryWarehouseRepository.findById(inventoryStockUpdateDto.getWarehouseId()).orElseThrow(InventoryWarehouseNotFoundException::new);
        InventoryProductSnapshot inventoryProductSnapshot = inventoryProductSnapshotRepository.findById(inventoryStockUpdateDto.getProductSnapshotId()).orElseThrow(InventoryProductSnapshotNotFoundException::new);

        boolean isWarehouseChanged = !inventoryStock.getWarehouse().getWarehouseId().equals(inventoryStockUpdateDto.getWarehouseId());
        boolean isProductSnapshotChanged = !inventoryStock.getProductSnapshot().getProductSnapshotId().equals(inventoryStockUpdateDto.getProductSnapshotId());
        if ((isWarehouseChanged || isProductSnapshotChanged) &&
                inventoryStockRepository.existsByWarehouseAndProductSnapshot(inventoryWarehouse, inventoryProductSnapshot)) {
            throw new InventoryStockAlreadyExistsException("Inventory stock with this warehouse and product already exists.");
        }

        return inventoryStockMapper.toInventoryStockDto(
                inventoryStockRepository.save(
                        inventoryStockMapper.toInventoryStock(
                                inventoryStockUpdateDto,
                                inventoryStock,
                                inventoryWarehouse,
                                inventoryProductSnapshot)
                )
        );
    }

    @Override
    @Transactional
    public InventoryStockDto addAvailableStock(Integer stockId, Integer quantityChange) throws InventoryStockNotFoundException {
        if(!inventoryStockRepository.existsById(stockId)){
            throw new InventoryStockNotFoundException();
        }
        inventoryStockRepository.addAvailableStock(stockId, quantityChange);
        InventoryStock inventoryStock = inventoryStockRepository.findById(stockId).orElseThrow(InventoryStockNotFoundException::new);
        return inventoryStockMapper.toInventoryStockDto(inventoryStock);
    }

    @Override
    @Transactional
    public InventoryStockDto deductAvailableStock(Integer stockId, Integer quantityChange) throws InventoryStockNotFoundException {
        if(!inventoryStockRepository.existsById(stockId)){
            throw new InventoryStockNotFoundException();
        }
        inventoryStockRepository.deductAvailableStock(stockId,quantityChange);
        InventoryStock inventoryStock = inventoryStockRepository.findById(stockId).orElseThrow(InventoryStockNotFoundException::new);
        return inventoryStockMapper.toInventoryStockDto(inventoryStock);
    }

    @Override
    @Transactional
    public void deleteInventoryStock(Integer stockId) throws InventoryStockNotFoundException {
        InventoryStock inventoryStock = inventoryStockRepository.findById(stockId).orElseThrow(InventoryStockNotFoundException::new);
        inventoryStockRepository.delete(inventoryStock);
    }
}
