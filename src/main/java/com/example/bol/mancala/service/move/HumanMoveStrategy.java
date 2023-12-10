package com.example.bol.mancala.service.move;

import com.example.bol.mancala.entity.MancalaGame;
import jdk.jshell.spi.ExecutionControl.NotImplementedException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class HumanMoveStrategy implements MoveStrategy {

    private final int pit;

    @Override
    public void move(MancalaGame game) throws NotImplementedException {
        throw new NotImplementedException("Human move strategy not implemented yet.");
    }
}
