package com.walkway.product_service.exception.product_item;

public class ProductItemNotFoundException extends Exception{
    public ProductItemNotFoundException() {
        super("Product Item with given attribute not found");
    }

    public ProductItemNotFoundException(String message) {
        super(message);
    }

    public ProductItemNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductItemNotFoundException(Throwable cause) {
        super(cause);
    }
}
