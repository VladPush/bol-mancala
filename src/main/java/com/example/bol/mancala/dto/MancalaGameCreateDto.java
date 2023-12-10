package com.example.bol.mancala.dto;

import com.example.bol.mancala.dto.enums.PlayerType;
import com.example.bol.mancala.validator.ValidPitsAmount;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Optional;


@RequiredArgsConstructor
@Getter
public final class MancalaGameCreateDto {
    @ValidPitsAmount
    private final int pitsAmount;
    @Positive
    private final int stones;
    private final Optional<PlayerType> playerA;
    private final Optional<PlayerType> playerB;

}
