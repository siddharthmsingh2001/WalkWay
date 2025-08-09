package com.walkway.account_service.exception.custom;

public class RealmAccountAlreadyExistException extends Exception{
    public RealmAccountAlreadyExistException() {
        super("Account with given Email already exists");
    }

    public RealmAccountAlreadyExistException(String message) {
        super(message);
    }

    public RealmAccountAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public RealmAccountAlreadyExistException(Throwable cause) {
        super(cause);
    }
}
