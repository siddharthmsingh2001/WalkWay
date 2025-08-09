package com.walkway.inventory_service.service.mapper;

import com.walkway.inventory_service.dto.inventory_product_snapshot.InventoryProductSnapshotCreateDto;
import com.walkway.inventory_service.dto.inventory_product_snapshot.InventoryProductSnapshotDto;
import com.walkway.inventory_service.dto.inventory_product_snapshot.InventoryProductSnapshotUpdateDto;
import com.walkway.inventory_service.entity.InventoryProductSnapshot;
import org.springframework.stereotype.Service;

@Service
public class InventoryProductSnapshotMapper {

    public InventoryProductSnapshot toInventoryProductSnapshot(InventoryProductSnapshotCreateDto inventoryProductSnapshotCreateDto){
        return InventoryProductSnapshot.builder()
                .productCode(inventoryProductSnapshotCreateDto.getProductCode())
                .build();
    }

    public InventoryProductSnapshot toInventoryProductSnapshot(InventoryProductSnapshot inventoryProductSnapshot, InventoryProductSnapshotUpdateDto inventoryProductSnapshotUpdateDto){
        return inventoryProductSnapshot
                .setProductCode(inventoryProductSnapshotUpdateDto.getProductCode());
    }

    public InventoryProductSnapshotDto toInventoryProductSnapshotDto(InventoryProductSnapshot inventoryProductSnapshot){
        return InventoryProductSnapshotDto.builder()
                .productSnapshotId(inventoryProductSnapshot.getProductSnapshotId())
                .productCode(inventoryProductSnapshot.getProductCode())
                .productSnapshotCreatedAt(inventoryProductSnapshot.getProductSnapshotCreatedAt())
                .productSnapshotUpdatedAt(inventoryProductSnapshot.getProductSnapshotUpdatedAt())
                .build();
    }
}
