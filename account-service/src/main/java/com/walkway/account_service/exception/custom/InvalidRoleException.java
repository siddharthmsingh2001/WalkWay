package com.walkway.account_service.exception.custom;

public class InvalidRoleException extends Exception{
    public InvalidRoleException() {
        super("Invalid Role");
    }

    public InvalidRoleException(String message) {
        super(message);
    }

    public InvalidRoleException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidRoleException(Throwable cause) {
        super(cause);
    }
}
