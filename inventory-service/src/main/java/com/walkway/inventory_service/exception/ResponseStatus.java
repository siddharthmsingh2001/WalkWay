package com.walkway.inventory_service.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ResponseStatus {
    INVENTORY_LOCATION_EXISTS_EXCEPTION(HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT),
    INVENTORY_LOCATION_MISSING_EXCEPTION(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND),
    INVENTORY_PRODUCT_SNAPSHOT_EXISTS_EXCEPTION(HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT),
    INVENTORY_PRODUCT_SNAPSHOT_MISSING_EXCEPTION(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND),
    INVENTORY_STOCK_EXISTS_EXCEPTION(HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT),
    INVENTORY_STOCK_MISSING_EXCEPTION(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND),
    INVENTORY_WAREHOUSE_EXISTS_EXCEPTION(HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT),
    INVENTORY_WAREHOUSE_MISSING_EXCEPTION(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND),
    BAD_JWT_EXCEPTION(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED),
    AUTHORIZATION_DENIED_EXCEPTION(HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN),
    VALIDATION_EXCEPTION(HttpStatus.BAD_REQUEST.value(),HttpStatus.BAD_REQUEST),
    JSON_PARSING_EXCEPTION(HttpStatus.BAD_REQUEST.value(),HttpStatus.BAD_REQUEST),
    INSUFFICIENT_AUTHENTICATION_EXCEPTION(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED),
    BAD_REQUEST(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST);

    private int statusCode;
    private HttpStatus statusMsg;

    ResponseStatus(int statusCode, HttpStatus statusMsg){
        this.statusCode = statusCode;
        this.statusMsg = statusMsg;
    }
}
