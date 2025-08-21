package com.walkway.inventory_service.exception.inventory_product_snapshot;

public class InventoryProductSnapshotAlreadyExistsException extends Exception{

    public InventoryProductSnapshotAlreadyExistsException() {
        super("Inventory Product Snapshot already exists");
    }

    public InventoryProductSnapshotAlreadyExistsException(String message) {
        super(message);
    }

    public InventoryProductSnapshotAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public InventoryProductSnapshotAlreadyExistsException(Throwable cause) {
        super(cause);
    }
}
