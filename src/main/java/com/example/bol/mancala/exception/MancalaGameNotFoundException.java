package com.example.bol.mancala.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.MessageFormat;
import java.util.UUID;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
@Setter
@NoArgsConstructor
public class MancalaGameNotFoundException extends Exception {

    private final static String template = "Mancala game with id {0} does not exist";
    private final int code = NOT_FOUND.value();

    public MancalaGameNotFoundException(UUID gameId) {
        super(MessageFormat.format(template, gameId));
    }
}
