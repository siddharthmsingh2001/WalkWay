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
@Table(name = "inventory_stock")
public class InventoryStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer stockId;

    @ManyToOne
    @JoinColumn(name = "stock_warehouse_id", referencedColumnName = "warehouseId", nullable = false)
    private InventoryWarehouse warehouse;

    @ManyToOne
    @JoinColumn(name = "stock_product_snapshot_id", referencedColumnName = "productSnapshotId", nullable = false)
    private InventoryProductSnapshot productSnapshot;

    @Column(name = "stock_quantity_available", nullable = false)
    private Integer stockQuantityAvailable;

    @Column(name = "stock_quantity_reserved")
    private Integer stockQuantityReserved;

    @Column(name = "stock_created_at")
    private LocalDateTime stockCreatedAt;

    @Column(name = "stock_updated_at")
    private LocalDateTime stockUpdatedAt;

    @PrePersist
    protected void onCreate() {
        stockCreatedAt = stockUpdatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        stockUpdatedAt = LocalDateTime.now();
    }

}
