package com.walkway.product_service.exception.product_gender;

public class ProductGenderAlreadyExistsException extends Exception{
    public ProductGenderAlreadyExistsException() {
        super("Product Gender already Exists");
    }

    public ProductGenderAlreadyExistsException(String message) {
        super(message);
    }

    public ProductGenderAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductGenderAlreadyExistsException(Throwable cause) {
        super(cause);
    }
}
