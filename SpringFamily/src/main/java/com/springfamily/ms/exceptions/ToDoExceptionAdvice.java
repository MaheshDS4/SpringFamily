package com.springfamily.ms.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ToDoExceptionAdvice {

    @ExceptionHandler(value = {ToDoException.class})
    public ResponseEntity<Object> handleToDoException(ToDoException toDoException, WebRequest request) {
        CustomErrorMessage errorMessage = new CustomErrorMessage();
        errorMessage.setMessage(toDoException.getMessage());
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {NullPointerException.class})
    public ResponseEntity<Object> handleNullPointerException(NullPointerException nullPointerException, WebRequest request) {
        CustomErrorMessage errorMessage = new CustomErrorMessage();
        errorMessage.setMessage(ErrorMessage.MISSING_REQUIRED_FIELD.getErrorMessage());
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
