package com.walkway.product_service.exception.product_colour;

public class ProductColourAlreadyExistsException extends Exception{

    public ProductColourAlreadyExistsException() {
        super("Product Colour already Exists");
    }

    public ProductColourAlreadyExistsException(String message) {
        super(message);
    }

    public ProductColourAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductColourAlreadyExistsException(Throwable cause) {
        super(cause);
    }
}
