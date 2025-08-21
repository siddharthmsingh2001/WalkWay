package com.walkway.account_service.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ResponseStatus {

    REALM_ACCOUNT_EXISTS_EXCEPTION(HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT),
    SERVICE_ACCOUNT_EXISTS_EXCEPTION(HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT),
    SERVICE_ACCOUNT_MISSING_EXCEPTION(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND),
    ACCOUNT_PROFILE_EXISTS_EXCEPTION(HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT),
    ACCOUNT_PROFILE_MISSING_EXCEPTION(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND),
    BAD_JWT_EXCEPTION(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED),
    AUTHORIZATION_DENIED_EXCEPTION(HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN),
    INSUFFICIENT_AUTHENTICATION_EXCEPTION(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED),
    VALIDATION_EXCEPTION(HttpStatus.BAD_REQUEST.value(),HttpStatus.BAD_REQUEST),
    JSON_PARSING_EXCEPTION(HttpStatus.BAD_REQUEST.value(),HttpStatus.BAD_REQUEST),
    BAD_REQUEST(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST);

    private int statusCode;
    private HttpStatus statusMsg;

    ResponseStatus (int statusCode, HttpStatus statusMsg){
        this.statusCode = statusCode;
        this.statusMsg = statusMsg;
    }
}
