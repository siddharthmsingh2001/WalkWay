package com.walkway.inventory_service.exception.inventory_stock;

public class InventoryStockAlreadyExistsException extends Exception{

    public InventoryStockAlreadyExistsException() {
        super("Inventory Stock already exists");
    }

    public InventoryStockAlreadyExistsException(String message) {
        super(message);
    }

    public InventoryStockAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public InventoryStockAlreadyExistsException(Throwable cause) {
        super(cause);
    }
}
