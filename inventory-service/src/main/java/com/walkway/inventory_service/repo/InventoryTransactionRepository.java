package com.walkway.inventory_service.repo;

import com.walkway.inventory_service.entity.InventoryStock;
import com.walkway.inventory_service.entity.InventoryTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryTransactionRepository extends JpaRepository<InventoryTransaction, Integer> {

    List<InventoryTransaction> findByStock(InventoryStock stock);

}
