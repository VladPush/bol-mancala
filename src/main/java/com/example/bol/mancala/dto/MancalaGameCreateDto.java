package com.example.bol.mancala.dto;

import com.example.bol.mancala.dto.enums.PlayerType;

import java.util.Optional;


public record MancalaGameCreateDto(int pitsAmount,
                                   int stones,
                                   Optional<PlayerType> playerA,
                                   Optional<PlayerType> playerB) {
}
