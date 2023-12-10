package com.example.bol.mancala.configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StringLiterals {
    LESS("less"), MORE("more");

    private final String name;

}
