package com.example.bol.mancala.service.move;

import com.example.bol.mancala.entity.MancalaGame;
import jdk.jshell.spi.ExecutionControl.NotImplementedException;

@FunctionalInterface
public interface MoveStrategy {

    void move(MancalaGame game) throws NotImplementedException;
}
