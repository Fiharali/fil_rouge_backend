package com.ali.hunter.exception;

public   class EmailAlreadyExisteException extends RuntimeException {
    public EmailAlreadyExisteException(String message) {
        super(message);
    }

}
