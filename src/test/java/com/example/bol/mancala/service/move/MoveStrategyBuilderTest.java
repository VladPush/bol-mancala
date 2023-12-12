package com.example.bol.mancala.service.move;

import com.example.bol.mancala.dto.enums.PlayerType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MoveStrategyBuilderTest {
    @Test
    public void buildHumanPlayer() {
        MoveStrategyBuilder.Components components = MoveStrategyBuilder.Components.builder()
                .pit(2)
                .build();
        MoveStrategy strategy = MoveStrategyBuilder.build(PlayerType.HUMAN, components);

        assertTrue(strategy instanceof HumanMoveStrategy);
    }

    @Test
    public void buildWithAiPlayer() {
        MoveStrategy strategy = MoveStrategyBuilder.build(PlayerType.AI, null);

        assertTrue(strategy instanceof AiMoveStrategy);
    }
}