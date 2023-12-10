package com.example.bol.mancala.service.move;

import com.example.bol.mancala.dto.enums.PlayerTurn;
import com.example.bol.mancala.entity.Pit;

import java.util.List;

import static jdk.jshell.spi.ExecutionControl.NotImplementedException;

public class AiMoveStrategy implements MoveStrategy {

    @Override
    public int getPitForMove(List<Pit> pits, PlayerTurn turn) throws NotImplementedException {
        throw new NotImplementedException("Ai move strategy not implemented yet.");
    }
}
