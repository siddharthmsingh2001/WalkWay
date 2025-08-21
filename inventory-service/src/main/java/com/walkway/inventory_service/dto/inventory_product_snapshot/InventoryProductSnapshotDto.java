package com.walkway.inventory_service.dto.inventory_product_snapshot;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryProductSnapshotDto {
    private Integer productSnapshotId;
    private Integer productCode;
    private LocalDateTime productSnapshotCreatedAt;
    private LocalDateTime productSnapshotUpdatedAt;
}
