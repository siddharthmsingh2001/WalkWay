package com.walkway.product_service.exception.product_category;

public class ProductCategoryAlreadyExistsException extends Exception{

    public ProductCategoryAlreadyExistsException() {
        super("Product Category already Exists");
    }

    public ProductCategoryAlreadyExistsException(String message) {
        super(message);
    }

    public ProductCategoryAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductCategoryAlreadyExistsException(Throwable cause) {
        super(cause);
    }
}
