package com.springfamily.ms.exceptions;

public class ToDoException extends RuntimeException {

    public ToDoException() {
    }

    public ToDoException(String message) {
        super(message);
    }

    public ToDoException(String message, Throwable cause) {
        super(message, cause);
    }

    public ToDoException(Throwable cause) {
        super(cause);
    }

    public ToDoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
