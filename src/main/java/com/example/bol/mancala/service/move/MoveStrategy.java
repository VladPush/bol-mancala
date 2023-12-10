package com.example.bol.mancala.service.move;

import com.example.bol.mancala.dto.enums.PlayerTurn;
import com.example.bol.mancala.entity.Pit;
import jdk.jshell.spi.ExecutionControl.NotImplementedException;

import java.util.List;

@FunctionalInterface
public interface MoveStrategy {

    int getPitForMove(List<Pit> pits, PlayerTurn turn) throws NotImplementedException;
}
