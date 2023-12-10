package com.example.bol.mancala.service.api;

import com.example.bol.mancala.dto.enums.PlayerTurn;
import com.example.bol.mancala.entity.Pit;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MancalaGameDefaultRulesApi implements MancalaGameApi {
    @Override
    public MovedMancalaGameDto move(List<Pit> pits, Integer pit) {
        return new MovedMancalaGameDto(pits, PlayerTurn.PlayerA);
    }
}
