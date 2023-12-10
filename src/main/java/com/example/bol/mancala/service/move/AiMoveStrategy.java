package com.example.bol.mancala.service.move;

import com.example.bol.mancala.entity.MancalaGame;

import static jdk.jshell.spi.ExecutionControl.NotImplementedException;

public class AiMoveStrategy implements MoveStrategy {
    @Override
    public void move(MancalaGame game) throws NotImplementedException {
        throw new NotImplementedException("Ai move strategy not implemented yet.");
    }
}
