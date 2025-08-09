package com.walkway.product_service.exception.product;

public class ProductAlreadyExistsException extends Exception{

    public ProductAlreadyExistsException() {
        super("Product already exists");
    }

    public ProductAlreadyExistsException(String message) {
        super(message);
    }

    public ProductAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductAlreadyExistsException(Throwable cause) {
        super(cause);
    }
}
