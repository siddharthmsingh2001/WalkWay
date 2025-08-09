package com.walkway.inventory_service.exception.inventory_location;

public class InventoryLocationAlreadyExistsException extends Exception{

    public InventoryLocationAlreadyExistsException() {
        super("Inventory Location already exists");
    }

    public InventoryLocationAlreadyExistsException(String message) {
        super(message);
    }

    public InventoryLocationAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public InventoryLocationAlreadyExistsException(Throwable cause) {
        super(cause);
    }
}
