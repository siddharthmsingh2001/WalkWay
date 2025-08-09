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
@Accessors(chain = true)
@Entity
@Table(name = "inventory_warehouse")
public class InventoryWarehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer warehouseId;

    @Column(name = "warehouse_name", nullable = false, unique = true)
    private String warehouseName;

    @ManyToOne
    @JoinColumn(name = "warehouse_location_id", referencedColumnName = "locationId", nullable = true)
    private InventoryLocation warehouseLocation;

    @Column(name = "warehouse_created_at")
    private LocalDateTime warehouseCreatedAt;

    @Column(name = "warehouse_updated_at")
    private LocalDateTime warehouseUpdatedAt;

    @PrePersist
    protected void onCreate() {
        warehouseCreatedAt = warehouseUpdatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        warehouseUpdatedAt = LocalDateTime.now();
    }

}
