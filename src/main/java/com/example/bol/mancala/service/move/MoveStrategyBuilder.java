package com.example.bol.mancala.service.move;

import com.example.bol.mancala.dto.enums.PlayerType;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MoveStrategyBuilder {

    public MoveStrategy build(PlayerType playerType, Components components) {
        return switch (playerType) {
            case HUMAN -> new HumanMoveStrategy(components.getPit());
            case AI -> new AiMoveStrategy();
        };
    }

    @Builder
    @Getter
    public class Components {
        private Integer pit;
    }

}
