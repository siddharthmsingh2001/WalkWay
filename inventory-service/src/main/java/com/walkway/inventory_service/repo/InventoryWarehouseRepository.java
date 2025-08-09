package com.walkway.inventory_service.repo;

import com.walkway.inventory_service.entity.InventoryWarehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryWarehouseRepository extends JpaRepository<InventoryWarehouse, Integer> {
    Optional<InventoryWarehouse> findByWarehouseName(String warehouseName);
    boolean existsByWarehouseName(String warehouseName);
}
