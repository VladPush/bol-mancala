package com.example.bol.mancala.service.move;

import com.example.bol.mancala.dto.enums.PlayerTurn;
import com.example.bol.mancala.entity.Pit;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HumanMoveStrategyTest {

    @Test
    public void getPitForMoveReturnCorrectResult() {
        List<Pit> pits = new ArrayList<>();
        PlayerTurn turn = PlayerTurn.PlayerA;

        HumanMoveStrategy strategy = new HumanMoveStrategy(2);
        int result = strategy.getPitForMove(pits, turn);

        assertEquals(2, result);
    }

    @Test
    public void constructorReturnExceptionOnNullInput() {
        assertThrows(IllegalArgumentException.class, () -> new HumanMoveStrategy(null));
    }

    @Test
    public void constructorReturnExceptionOnNegativeInput() {
        assertThrows(IllegalArgumentException.class, () -> new HumanMoveStrategy(-7));
    }

    @Test
    public void constructorReturnExceptionOnZeroInput() {
        assertThrows(IllegalArgumentException.class, () -> new HumanMoveStrategy(0));
    }
}