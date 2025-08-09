package com.walkway.account_service.exception.custom;

public class AccountProfileAlreadyExistException extends Exception{

    public AccountProfileAlreadyExistException() {
        super("Account Profile for given email already exists");
    }

    public AccountProfileAlreadyExistException(String message) {
        super(message);
    }

    public AccountProfileAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccountProfileAlreadyExistException(Throwable cause) {
        super(cause);
    }
}
