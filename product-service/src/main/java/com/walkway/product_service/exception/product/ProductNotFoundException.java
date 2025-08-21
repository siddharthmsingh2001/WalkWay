package com.walkway.product_service.exception.product;

public class ProductNotFoundException extends Exception{

    public ProductNotFoundException() {
        super("Product with given attribute not found");
    }

    public ProductNotFoundException(String message) {
        super(message);
    }

    public ProductNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductNotFoundException(Throwable cause) {
        super(cause);
    }
}
