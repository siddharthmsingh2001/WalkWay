package com.walkway.product_service.exception.product_image;

public class ProductImageNotFoundException extends Exception{

    public ProductImageNotFoundException() {
        super("Product Image for the given attribute does not exists");
    }

    public ProductImageNotFoundException(String message) {
        super(message);
    }

    public ProductImageNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductImageNotFoundException(Throwable cause) {
        super(cause);
    }
}
