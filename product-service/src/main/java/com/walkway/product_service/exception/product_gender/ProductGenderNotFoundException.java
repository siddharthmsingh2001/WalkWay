package com.walkway.product_service.exception.product_gender;

public class ProductGenderNotFoundException extends Exception{

    public ProductGenderNotFoundException() {
        super("Product Gender for the given attribute not found");
    }

    public ProductGenderNotFoundException(String message) {
        super(message);
    }

    public ProductGenderNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductGenderNotFoundException(Throwable cause) {
        super(cause);
    }
}
