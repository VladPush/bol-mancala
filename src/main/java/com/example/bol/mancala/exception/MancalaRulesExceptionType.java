package com.example.bol.mancala.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MancalaRulesExceptionType {
    EMPTY_PIT("Player shouldn't start from empty pit"),
    HOUSE_MOVE_PIT("Player shouldn't start from house pits"),
    NOT_OWNER_MOVE_PIT("Player should start from his own pits"),
    INCORRECT_PIT_NUMBER("Attempt to get incorrect pit number");

    private final String message;
}
