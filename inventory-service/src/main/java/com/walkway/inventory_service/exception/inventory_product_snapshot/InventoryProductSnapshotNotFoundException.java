package com.walkway.inventory_service.exception.inventory_product_snapshot;

public class InventoryProductSnapshotNotFoundException extends Exception{

    public InventoryProductSnapshotNotFoundException() {
        super("Inventory Product Snapshot not found");
    }

    public InventoryProductSnapshotNotFoundException(String message) {
        super(message);
    }

    public InventoryProductSnapshotNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public InventoryProductSnapshotNotFoundException(Throwable cause) {
        super(cause);
    }
}
