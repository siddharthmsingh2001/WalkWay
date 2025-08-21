package com.walkway.product_service.exception.product_image;

public class ProductImageAlreadyExistsException extends Exception{

    public ProductImageAlreadyExistsException() {
        super("Product Image already exists");
    }

    public ProductImageAlreadyExistsException(String message) {
        super(message);
    }

    public ProductImageAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductImageAlreadyExistsException(Throwable cause) {
        super(cause);
    }
}
