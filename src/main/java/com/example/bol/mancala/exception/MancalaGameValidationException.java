package com.example.bol.mancala.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@Getter
@Setter
@NoArgsConstructor
public class MancalaGameValidationException extends RuntimeException {

    private final int code = UNPROCESSABLE_ENTITY.value();

    public MancalaGameValidationException(List<String> validationErrorsMessages) {
        super(String.join(System.lineSeparator(), validationErrorsMessages));
    }
}
