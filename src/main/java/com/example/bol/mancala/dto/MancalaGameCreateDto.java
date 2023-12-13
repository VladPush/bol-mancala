package com.example.bol.mancala.dto;

import com.example.bol.mancala.dto.enums.PlayerType;
import com.example.bol.mancala.validator.ValidPitsAmount;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Optional;


@AllArgsConstructor
@Getter
@NoArgsConstructor
public class MancalaGameCreateDto {
    @ValidPitsAmount
    private int pitsAmount;
    @Positive
    private int stones;
    private Optional<PlayerType> playerA;
    private Optional<PlayerType> playerB;

}
