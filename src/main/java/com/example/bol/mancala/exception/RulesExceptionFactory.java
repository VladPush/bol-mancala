package com.example.bol.mancala.exception;

import static com.example.bol.mancala.exception.MancalaRulesExceptionType.*;

public class RulesExceptionFactory {

    public static MancalaGameRulesException createException(MancalaRulesExceptionType exceptionType) {
        return switch (exceptionType) {
            case EMPTY_PIT -> new MancalaGameRulesException(EMPTY_PIT.getMessage());
            case HOUSE_MOVE_PIT -> new MancalaGameRulesException(HOUSE_MOVE_PIT.getMessage());
            case NOT_OWNER_MOVE_PIT -> new MancalaGameRulesException(NOT_OWNER_MOVE_PIT.getMessage());
            case INCORRECT_PIT_NUMBER -> new MancalaGameRulesException(INCORRECT_PIT_NUMBER.getMessage());
            case INCORRECT_PIT_NUMBER_OUT_OF_RANGE ->
                    new MancalaGameRulesException(INCORRECT_PIT_NUMBER_OUT_OF_RANGE.getMessage());
        };
    }
}
