package com.walkway.inventory_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Accessors(chain = true)
@Table(name = "inventory_product_snapshot")
public class InventoryProductSnapshot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productSnapshotId;

    @Column(name = "product_code", nullable = false)
    private Integer productCode;

    @Column(name = "product_snapshot_created_at")
    private LocalDateTime productSnapshotCreatedAt;

    @Column(name = "product_snapshot_updated_at")
    private LocalDateTime productSnapshotUpdatedAt;

    @PrePersist
    protected void onCreate() {
        productSnapshotCreatedAt = productSnapshotUpdatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        productSnapshotUpdatedAt = LocalDateTime.now();
    }

}
