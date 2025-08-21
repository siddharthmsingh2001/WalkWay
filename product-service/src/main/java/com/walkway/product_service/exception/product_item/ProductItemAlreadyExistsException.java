package com.walkway.product_service.exception.product_item;

public class ProductItemAlreadyExistsException extends Exception{
    public ProductItemAlreadyExistsException() {
        super("Product Item already Exists");
    }

    public ProductItemAlreadyExistsException(String message) {
        super(message);
    }

    public ProductItemAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductItemAlreadyExistsException(Throwable cause) {
        super(cause);
    }
}
