package com.example.bol.mancala.service.api;

import com.example.bol.mancala.dto.enums.PlayerTurn;
import com.example.bol.mancala.entity.MancalaGame;
import com.example.bol.mancala.entity.Pit;
import com.example.bol.mancala.exception.MancalaGameRulesException;
import com.example.bol.mancala.test.helper.builder.MancalaGameEntityBuilder;
import com.example.bol.mancala.util.PitUtils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.example.bol.mancala.exception.MancalaRulesExceptionType.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MancalaGameDefaultRulesApiTest {


    private final MancalaGameApi mancalaGameApi = new MancalaGameDefaultRulesApi();

    @Test
    public void moveFromZeroReturnException() {
        List<Pit> pits = PitUtils.createPits(2, 3);
        pits.get(1).setStones(0);
        MancalaGame mancalaGame = MancalaGameEntityBuilder.aGame().withPits(pits).build();


        MancalaGameRulesException mancalaGameRulesException = assertThrows(MancalaGameRulesException.class, () -> mancalaGameApi.move(mancalaGame, 2));
        assertEquals(mancalaGameRulesException.getMessage(), EMPTY_PIT.getMessage());
    }

    @Test
    public void moveFromOpponentPitPlayerBReturnException() {
        List<Pit> pitsWithEmptyPit = PitUtils.createPits(2, 3);
        MancalaGame mancalaGame = MancalaGameEntityBuilder.aGame().withPits(pitsWithEmptyPit).build();


        MancalaGameRulesException mancalaGameRulesException = assertThrows(MancalaGameRulesException.class, () -> mancalaGameApi.move(mancalaGame, 4));
        assertEquals(mancalaGameRulesException.getMessage(), NOT_OWNER_MOVE_PIT.getMessage());
    }

    @Test
    public void moveFromOpponentPitPlayerAReturnException() {
        List<Pit> pitsWithEmptyPit = PitUtils.createPits(2, 3);
        MancalaGame mancalaGame = MancalaGameEntityBuilder.aGame().withPits(pitsWithEmptyPit).withTurn(PlayerTurn.PlayerB).build();


        MancalaGameRulesException mancalaGameRulesException = assertThrows(MancalaGameRulesException.class, () -> mancalaGameApi.move(mancalaGame, 2));
        assertEquals(mancalaGameRulesException.getMessage(), NOT_OWNER_MOVE_PIT.getMessage());
    }

    @Test
    public void moveFromHouseReturnException() {
        List<Pit> pitsWithEmptyPit = PitUtils.createPits(2, 3);
        MancalaGame mancalaGame = MancalaGameEntityBuilder.aGame().withPits(pitsWithEmptyPit).build();

        MancalaGameRulesException mancalaGameRulesException = assertThrows(MancalaGameRulesException.class, () -> mancalaGameApi.move(mancalaGame, 3));
        assertEquals(mancalaGameRulesException.getMessage(), HOUSE_MOVE_PIT.getMessage());
    }


    @Test
    public void moveSimple() {
        int stones = 3;
        List<Pit> pits = PitUtils.createPits(3, stones);
        MancalaGame game = MancalaGameEntityBuilder.aGame().withPits(pits).build();
        int movePitStartPosition = 2;

        mancalaGameApi.move(game, movePitStartPosition);

        assertEquals(0, pits.get(movePitStartPosition - 1).getStones());
        assertEquals(stones + 1, pits.get(movePitStartPosition).getStones());
        assertEquals(1, pits.get(movePitStartPosition + 1).getStones());
        assertEquals(stones + 1, pits.get(movePitStartPosition + 2).getStones());
        assertEquals(PlayerTurn.PlayerB, game.getTurn());
    }

    @Test
    public void moveThrowTwoHouses() {
        int stones = 8;
        List<Pit> pits = PitUtils.createPits(3, stones);
        MancalaGame game = MancalaGameEntityBuilder.aGame().withPits(pits).build();

        mancalaGameApi.move(game, 2);

        assertEquals(stones + 1, pits.get(0).getStones());
        assertEquals(1, pits.get(1).getStones());
        assertEquals(stones + 2, pits.get(2).getStones());
        assertEquals(1, pits.get(3).getStones());
        assertEquals(stones + 1, pits.get(4).getStones());
        assertEquals(stones + 1, pits.get(5).getStones());
        assertEquals(stones + 1, pits.get(6).getStones());
        assertEquals(0, pits.get(7).getStones());

        assertEquals(PlayerTurn.PlayerB, game.getTurn());
    }

    @Test
    public void moveLastStonePlayerAInEmptyOwnPit() {
        int stones = 8;
        List<Pit> pits = PitUtils.createPits(3, stones);
        pits.get(0).setStones(1);
        pits.get(1).setStones(0);
        MancalaGame game = MancalaGameEntityBuilder.aGame().withPits(pits).build();

        mancalaGameApi.move(game, 1);
        assertEquals(stones + 1, pits.get(3).getStones());
        assertEquals(PlayerTurn.PlayerB, game.getTurn());
    }

    @Test
    public void moveLastStonePlayerBInEmptyOwnPit() {
        int stones = 8;
        List<Pit> pits = PitUtils.createPits(3, stones);
        pits.get(5).setStones(1);
        pits.get(6).setStones(0);
        MancalaGame game = MancalaGameEntityBuilder.aGame().withPits(pits)
                .withTurn(PlayerTurn.PlayerB).build();

        mancalaGameApi.move(game, 6);
        assertEquals(stones + 1, pits.get(7).getStones());
        assertEquals(PlayerTurn.PlayerA, game.getTurn());
    }

    @Test
    public void moveLastStoneInOwnHouseSaveTurn() {
        List<Pit> pits = PitUtils.createPits(3, 8);
        pits.get(6).setStones(1);
        PlayerTurn savedTurn = PlayerTurn.PlayerB;
        MancalaGame game = MancalaGameEntityBuilder.aGame()
                .withPits(pits)
                .withTurn(savedTurn)
                .build();

        mancalaGameApi.move(game, 7);
        assertEquals(1, pits.get(7).getStones());
        assertEquals(savedTurn, game.getTurn());
    }
}