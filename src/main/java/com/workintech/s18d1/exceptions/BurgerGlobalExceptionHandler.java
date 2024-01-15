package com.workintech.s18d1.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BurgerGlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleBurgerException(BurgerException exception) {
        ExceptionResponse exceptionResponse= new ExceptionResponse(exception.getMessage());
        return new ResponseEntity<>(exceptionResponse, exception.getHttpStatus());
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleAllException(Exception exception) {
        ExceptionResponse exceptionResponse= new ExceptionResponse(exception.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
