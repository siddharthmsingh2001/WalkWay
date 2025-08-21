package com.walkway.inventory_service.repo;

import com.walkway.inventory_service.entity.InventoryProductSnapshot;
import com.walkway.inventory_service.entity.InventoryStock;
import com.walkway.inventory_service.entity.InventoryWarehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryStockRepository extends JpaRepository<InventoryStock, Integer> {

    Optional<InventoryStock> findByWarehouseAndProductSnapshot(InventoryWarehouse inventoryWarehouse, InventoryProductSnapshot inventoryProductSnapshot);

    boolean existsByWarehouseAndProductSnapshot(InventoryWarehouse inventoryWarehouse, InventoryProductSnapshot inventoryProductSnapshot);

    List<InventoryStock> findByWarehouse(InventoryWarehouse inventoryWarehouse);

    List<InventoryStock> findByProductSnapshot(InventoryProductSnapshot inventoryProductSnapshot);

    @Modifying
    @Query("UPDATE InventoryStock inventory_stock SET inventory_stock.stockQuantityAvailable = inventory_stock.stockQuantityAvailable + :stockChange WHERE inventory_stock.stockId = :stockId")
    int addAvailableStock(@Param("stockId") Integer stockId, @Param("stockChange") Integer stockChange);

    @Modifying
    @Query("UPDATE InventoryStock inventory_stock SET inventory_stock.stockQuantityReserved = inventory_stock.stockQuantityReserved + :stockChange WHERE inventory_stock.stockId = :stockId")
    int addReservedStock(@Param("stockId") Integer stockId, @Param("stockChange") Integer stockChange);

    @Modifying
    @Query("UPDATE InventoryStock inventory_stock SET inventory_stock.stockQuantityAvailable = inventory_stock.stockQuantityAvailable - :stockChange WHERE inventory_stock.stockId = :stockId AND inventory_stock.stockQuantityAvailable >= :stockChange")
    int deductAvailableStock(@Param("stockId") Integer stockId, @Param("stockChange") Integer stockChange);

    @Modifying
    @Query("UPDATE InventoryStock inventory_stock SET inventory_stock.stockQuantityReserved = inventory_stock.stockQuantityReserved - :stockChange WHERE inventory_stock.stockId = :stockId AND inventory_stock.stockQuantityReserved >= :stockChange")
    int deductReservedStock(@Param("stockId") Integer stockId, @Param("stockChange") Integer stockChange);
}
