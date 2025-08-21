package com.walkway.inventory_service.exception.inventory_location;

public class InventoryLocationNotFoundException extends Exception{

    public InventoryLocationNotFoundException() {
        super("Inventory Location already exists");
    }

    public InventoryLocationNotFoundException(String message) {
        super(message);
    }

    public InventoryLocationNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public InventoryLocationNotFoundException(Throwable cause) {
        super(cause);
    }
}
