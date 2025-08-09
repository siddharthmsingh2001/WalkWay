package com.walkway.inventory_service.repo;

import com.walkway.inventory_service.entity.InventoryLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryLocationRepository extends JpaRepository<InventoryLocation, Integer> {
}
