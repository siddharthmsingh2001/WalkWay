package com.walkway.product_service.exception.product_category;

public class ProductCategoryNotFoundException extends Exception{

    public ProductCategoryNotFoundException() {
        super("Product Category for the given attribute not found");
    }

    public ProductCategoryNotFoundException(String message) {
        super(message);
    }

    public ProductCategoryNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductCategoryNotFoundException(Throwable cause) {
        super(cause);
    }
}
