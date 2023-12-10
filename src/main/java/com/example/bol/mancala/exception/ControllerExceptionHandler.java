package com.example.bol.mancala.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    private static final StackTraceElement[] stackTrace = {};

    @ExceptionHandler(MancalaGameNotFoundException.class)
    public ResponseEntity<? extends Exception> handleBadRequestException(MancalaGameNotFoundException ex) {
        ex.setStackTrace(stackTrace);
        return ResponseEntity.status(ex.getCode()).body(ex);
    }
}
