package com.walkway.inventory_service.exception.inventory_warehouse;

public class InventoryWarehouseAlreadyExistsException extends Exception{

    public InventoryWarehouseAlreadyExistsException() {
        super("Inventory Warehouse already exists");
    }

    public InventoryWarehouseAlreadyExistsException(String message) {
        super(message);
    }

    public InventoryWarehouseAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public InventoryWarehouseAlreadyExistsException(Throwable cause) {
        super(cause);
    }
}
