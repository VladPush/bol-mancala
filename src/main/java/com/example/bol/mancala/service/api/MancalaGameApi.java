package com.example.bol.mancala.service.api;

import com.example.bol.mancala.dto.enums.PlayerTurn;
import com.example.bol.mancala.entity.Pit;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@FunctionalInterface
public interface MancalaGameApi {
    MovedMancalaGameDto move(List<Pit> pits, Integer pit);

    @AllArgsConstructor
    @Getter
    public class MovedMancalaGameDto {
        private final List<Pit> pits;
        private final PlayerTurn nextTurn;
    }
}
