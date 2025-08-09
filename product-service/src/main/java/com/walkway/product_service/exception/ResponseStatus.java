package com.walkway.product_service.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ResponseStatus {
    PRODUCT_GENDER_EXISTS_EXCEPTION(HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT),
    PRODUCT_GENDER_MISSING_EXCEPTION(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND),
    PRODUCT_COLOUR_EXISTS_EXCEPTION(HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT),
    PRODUCT_COLOUR_MISSING_EXCEPTION(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND),
    PRODUCT_SIZE_EXISTS_EXCEPTION(HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT),
    PRODUCT_SIZE_MISSING_EXCEPTION(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND),
    PRODUCT_CATEGORY_EXISTS_EXCEPTION(HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT),
    PRODUCT_CATEGORY_MISSING_EXCEPTION(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND),
    PRODUCT_EXISTS_EXCEPTION(HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT),
    PRODUCT_MISSING_EXCEPTION(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND),
    PRODUCT_ITEM_EXISTS_EXCEPTION(HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT),
    PRODUCT_ITEM_MISSING_EXCEPTION(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND),
    BAD_JWT_EXCEPTION(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED),
    AUTHORIZATION_DENIED_EXCEPTION(HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN),
    VALIDATION_EXCEPTION(HttpStatus.BAD_REQUEST.value(),HttpStatus.BAD_REQUEST),
    JSON_PARSING_EXCEPTION(HttpStatus.BAD_REQUEST.value(),HttpStatus.BAD_REQUEST),
    INSUFFICIENT_AUTHENTICATION_EXCEPTION(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED),
    BAD_REQUEST(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST);

    private int statusCode;
    private HttpStatus statusMsg;

    ResponseStatus (int statusCode, HttpStatus statusMsg){
        this.statusCode = statusCode;
        this.statusMsg = statusMsg;
    }
}
