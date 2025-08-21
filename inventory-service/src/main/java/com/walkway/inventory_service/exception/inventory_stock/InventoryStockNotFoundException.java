package com.walkway.inventory_service.exception.inventory_stock;

public class InventoryStockNotFoundException extends Exception{

    public InventoryStockNotFoundException() {
        super("Inventory Stock not found");
    }

    public InventoryStockNotFoundException(String message) {
        super(message);
    }

    public InventoryStockNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public InventoryStockNotFoundException(Throwable cause) {
        super(cause);
    }
}
