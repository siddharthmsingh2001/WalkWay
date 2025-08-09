package com.walkway.account_service.exception.custom;


public class ServiceAccountAlreadyExistException extends Exception{

    public ServiceAccountAlreadyExistException() {
        super("Account with given credential already exists");
    }

    public ServiceAccountAlreadyExistException(String message) {
        super(message);
    }

    public ServiceAccountAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceAccountAlreadyExistException(Throwable cause) {
        super(cause);
    }

}
