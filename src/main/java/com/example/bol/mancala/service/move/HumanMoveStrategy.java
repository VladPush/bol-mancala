package com.example.bol.mancala.service.move;

import com.example.bol.mancala.dto.enums.PlayerTurn;
import com.example.bol.mancala.entity.Pit;

import java.util.List;


public class HumanMoveStrategy implements MoveStrategy {

    private final Integer pit;

    public HumanMoveStrategy(Integer pit) {
        if (pit == null) {
            throw new IllegalArgumentException("Please specify pit number for this strategy"); //TODO own exception
        }
        this.pit = pit;
    }

    @Override
    public int getPitForMove(List<Pit> pits, PlayerTurn turn) {
        return pit;
    }
}
