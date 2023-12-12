package com.example.bol.mancala.service.api;

import com.example.bol.mancala.dto.enums.PlayerTurn;
import com.example.bol.mancala.entity.MancalaGame;
import com.example.bol.mancala.entity.Pit;
import com.example.bol.mancala.exception.RulesExceptionFactory;
import com.example.bol.mancala.util.PitUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.example.bol.mancala.exception.MancalaRulesExceptionType.*;
import static com.example.bol.mancala.util.PitUtils.ZERO_STONES;

@Component
public class MancalaGameDefaultRulesApi implements MancalaGameApi {
    private static boolean isItEmptyOwnPit(Pit targetPit, MancalaGame game, MoveSupportDto supportDto) {
        return PitUtils.isEmpty(targetPit) &&
                (game.getTurn() == PlayerTurn.PlayerA && targetPit.getNumber() < supportDto.getPlayerAHousePitNumber() ||
                        game.getTurn() == PlayerTurn.PlayerB && targetPit.getNumber() > supportDto.getPlayerAHousePitNumber());
    }

    private static void placeStone(MoveSupportDto supportDto, Pit targetPit) {
        PitUtils.addStone(targetPit);
        supportDto.decrementStones();
    }

    @Override
    public MancalaGame move(MancalaGame game, Integer pit) {
        List<Pit> pits = game.getPits();
        int playerBHousePitNumber = pits.size();
        int playerAHousePitNumber = playerBHousePitNumber / 2;

        if (pit == playerAHousePitNumber || pit == playerBHousePitNumber)
            throw RulesExceptionFactory.createException(HOUSE_MOVE_PIT);

        if (game.getTurn() == PlayerTurn.PlayerA && pit > playerAHousePitNumber || game.getTurn() == PlayerTurn.PlayerB && pit < playerAHousePitNumber)
            throw RulesExceptionFactory.createException(NOT_OWNER_MOVE_PIT);

        Pit selectedPit = PitUtils.getPit(pits, pit);
        int stones = selectedPit.getStones();

        if (stones == ZERO_STONES)
            throw RulesExceptionFactory.createException(EMPTY_PIT);

        selectedPit.setStones(ZERO_STONES);

        MoveSupportDto supportDto = new MoveSupportDto(pit, stones, playerAHousePitNumber, playerBHousePitNumber);

        for (int step = 1; step <= stones; step++) {
            move(game, supportDto);
        }

        /* Here check if fields of one player are empty -> collect all stones in appropriate house pits */

        switchTurn(game, supportDto);

        return game;
    }

    private void switchTurn(MancalaGame game, MoveSupportDto supportDto) {
        if (currentPitIndexNotAHouse(supportDto)) {
            PlayerTurn currentTurn = game.getTurn() == PlayerTurn.PlayerA ? PlayerTurn.PlayerB : PlayerTurn.PlayerA;
            game.setTurn(currentTurn);
        }
    }

    private boolean currentPitIndexNotAHouse(MoveSupportDto supportDto) {
        return supportDto.getCurrentPitIndex() != supportDto.getPlayerAHousePitNumber() && supportDto.getCurrentPitIndex() != supportDto.getPlayerBHousePitNumber();
    }

    private void move(MancalaGame game, MoveSupportDto supportDto) {
        int currentPitIndex = supportDto.getCurrentPitIndex() % supportDto.getTotalPits() + 1;

        if (isItOppositePlayerHouse(game, supportDto, currentPitIndex)) {
            currentPitIndex = currentPitIndex % supportDto.getTotalPits() + 1;
        }
        supportDto.setCurrentPitIndex(currentPitIndex);

        Pit targetPit = PitUtils.getPit(game.getPits(), currentPitIndex);

        if (supportDto.getStones() != 1) {
            placeStone(supportDto, targetPit);
            return;
        }

        Pit oppositePit = PitUtils.getPit(game.getPits(), supportDto.getTotalPits() - currentPitIndex);
        if (isItEmptyOwnPit(targetPit, game, supportDto) && !PitUtils.isEmpty(oppositePit)) {
            int oppositeStones = oppositePit.getStones();
            PitUtils.clear(oppositePit);
            int pitHouseIndex = currentPitIndex < supportDto.getPlayerAHousePitNumber() ? supportDto.getPlayerAHousePitNumber() : supportDto.getPlayerBHousePitNumber();
            Pit pitHouse = PitUtils.getPit(game.getPits(), pitHouseIndex);
            PitUtils.addStones(pitHouse, oppositeStones + 1);
            supportDto.decrementStones();
        } else {
            placeStone(supportDto, targetPit);
        }
    }

    private boolean isItOppositePlayerHouse(MancalaGame game, MoveSupportDto supportDto, int currentPitIndex) {
        return (currentPitIndex == supportDto.getPlayerAHousePitNumber() && game.getTurn() == PlayerTurn.PlayerB) ||
                (currentPitIndex == supportDto.getPlayerBHousePitNumber() && game.getTurn() == PlayerTurn.PlayerA);
    }

    @AllArgsConstructor
    @Getter
    private static class MoveSupportDto {
        private final int playerAHousePitNumber;
        private final int playerBHousePitNumber;
        private int currentPitIndex;
        private int stones;

        public int getTotalPits() {
            return playerBHousePitNumber;
        }

        public void setCurrentPitIndex(int currentPitIndex) {
            this.currentPitIndex = currentPitIndex;
        }

        public void decrementStones() {
            if (stones == 0) {
                throw new RuntimeException("Attempt to decrement already empty stones");
            }
            stones--;
        }
    }
}
