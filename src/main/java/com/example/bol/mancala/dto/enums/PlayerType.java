package com.example.bol.mancala.dto.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PlayerType {
    HUMAN("Human"), AI("Ai");

    private final String value;
}
