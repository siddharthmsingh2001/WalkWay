package com.walkway.product_service.exception.product_size;

public class ProductSizeAlreadyExistsException extends Exception{

    public ProductSizeAlreadyExistsException() {
        super("Product Size already Exists");
    }

    public ProductSizeAlreadyExistsException(String message) {
        super(message);
    }

    public ProductSizeAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductSizeAlreadyExistsException(Throwable cause) {
        super(cause);
    }
}
