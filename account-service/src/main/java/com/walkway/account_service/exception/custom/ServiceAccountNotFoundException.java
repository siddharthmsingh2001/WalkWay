package com.walkway.account_service.exception.custom;

public class ServiceAccountNotFoundException extends Exception{
    public ServiceAccountNotFoundException() {
        super("Account with given credential does not exists");
    }

    public ServiceAccountNotFoundException(String message) {
        super(message);
    }

    public ServiceAccountNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceAccountNotFoundException(Throwable cause) {
        super(cause);
    }

}
