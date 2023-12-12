package com.example.bol.mancala.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@RestControllerAdvice
public class ControllerExceptionHandler {

    private static final StackTraceElement[] stackTrace = {};

    @ExceptionHandler(MancalaGameNotFoundException.class)
    public ResponseEntity<? extends Exception> handleBadRequestException(MancalaGameNotFoundException ex) {
        ex.setStackTrace(stackTrace);
        return ResponseEntity.status(ex.getCode()).body(ex);
    }

    @ExceptionHandler(MancalaGameRulesException.class)
    public ResponseEntity<? extends Exception> handleBadRequestException(MancalaGameRulesException ex) {
        ex.setStackTrace(stackTrace);
        return ResponseEntity.status(ex.getCode()).body(ex);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<? extends Exception> handleBadRequestException(MethodArgumentNotValidException ex) {
        List<String> validationErrorsMessages = ex.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();

        MancalaGameValidationException validationException = new MancalaGameValidationException(validationErrorsMessages);
        validationException.setStackTrace(stackTrace);
        return ResponseEntity.status(UNPROCESSABLE_ENTITY).body(validationException);
    }
}
