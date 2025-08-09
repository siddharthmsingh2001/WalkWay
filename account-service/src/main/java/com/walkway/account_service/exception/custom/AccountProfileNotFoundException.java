package com.walkway.account_service.exception.custom;

public class AccountProfileNotFoundException extends Exception{
    public AccountProfileNotFoundException() {
        super("Account Profile for given email does not exists");
    }

    public AccountProfileNotFoundException(String message) {
        super(message);
    }

    public AccountProfileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccountProfileNotFoundException(Throwable cause) {
        super(cause);
    }
}
