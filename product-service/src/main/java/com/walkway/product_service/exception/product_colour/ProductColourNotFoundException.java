package com.walkway.product_service.exception.product_colour;

public class ProductColourNotFoundException extends Exception{

    public ProductColourNotFoundException() {
        super("Product Colour for the given attribute not found");
    }

    public ProductColourNotFoundException(String message) {
        super(message);
    }

    public ProductColourNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductColourNotFoundException(Throwable cause) {
        super(cause);
    }
}
