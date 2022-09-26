package com.springfamily.ms.exceptions;

public enum ErrorMessage {

    MISSING_REQUIRED_FIELD("Missing required field"),
    INTERNAL_SERVER_ERROR("Internal Server Error");

    private String errorMessage;

    ErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
