package com.example.bol.mancala.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@Getter
@Setter
@NoArgsConstructor
public class MancalaGameRulesException extends RuntimeException {

    private final int code = UNPROCESSABLE_ENTITY.value();

    public MancalaGameRulesException(String message) {
        super(message);
    }

}
