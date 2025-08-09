package com.walkway.inventory_service.exception.inventory_warehouse;

public class InventoryWarehouseNotFoundException extends Exception{

    public InventoryWarehouseNotFoundException() {
        super("Inventory Warehouse not found");
    }

    public InventoryWarehouseNotFoundException(String message) {
        super(message);
    }

    public InventoryWarehouseNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public InventoryWarehouseNotFoundException(Throwable cause) {
        super(cause);
    }
}
