package com.walkway.product_service.exception.product_size;

public class ProductSizeNotFoundException extends Exception{

    public ProductSizeNotFoundException() {
        super("Product Size for the given attribute not found");
    }

    public ProductSizeNotFoundException(String message) {
        super(message);
    }

    public ProductSizeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductSizeNotFoundException(Throwable cause) {
        super(cause);
    }
}
