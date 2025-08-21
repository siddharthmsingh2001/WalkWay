package com.walkway.inventory_service.repo;

import com.walkway.inventory_service.entity.InventoryProductSnapshot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryProductSnapshotRepository extends JpaRepository<InventoryProductSnapshot, Integer> {
    Optional<InventoryProductSnapshot> findByProductCode(Integer productCode);
    boolean existsByProductCode(Integer productCode);
}
