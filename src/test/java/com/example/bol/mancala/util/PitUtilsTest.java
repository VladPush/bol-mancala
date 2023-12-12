package com.example.bol.mancala.util;

import com.example.bol.mancala.entity.Pit;
import com.example.bol.mancala.exception.MancalaGameRulesException;
import com.example.bol.mancala.exception.MancalaRulesExceptionType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PitUtilsTest {

    @Test
    public void clear() {
        Pit pit = new Pit(1, 5);
        PitUtils.clear(pit);

        assertEquals(0, pit.getStones());
    }

    @Test
    public void isEmpty() {
        Pit emptyPit = new Pit(1, 0);
        Pit nonEmptyPit = new Pit(2, 3);

        assertTrue(PitUtils.isEmpty(emptyPit));
        assertFalse(PitUtils.isEmpty(nonEmptyPit));
    }

    @Test
    public void addStone() {
        Pit pit = new Pit(1, 3);
        PitUtils.addStone(pit);

        assertEquals(4, pit.getStones());
    }

    @Test
    public void addStones() {
        Pit pit = new Pit(1, 3);
        PitUtils.addStones(pit, 2);

        assertEquals(5, pit.getStones());
    }

    @Test
    public void getPit() {
        List<Pit> pits = new ArrayList<>();
        pits.add(new Pit(1, 3));
        pits.add(new Pit(2, 5));

        assertEquals(pits.get(0), PitUtils.getPit(pits, 1));
        assertEquals(pits.get(1), PitUtils.getPit(pits, 2));

        MancalaGameRulesException resultException = assertThrows(MancalaGameRulesException.class, () -> PitUtils.getPit(pits, 3));
        assertEquals(MancalaRulesExceptionType.INCORRECT_PIT_NUMBER.getMessage(), resultException.getMessage());
    }

    @Test
    public void createPits() {
        ArrayList<Pit> pits = PitUtils.createPits(2, 5);

        assertEquals(6, pits.size());
        assertEquals(5, pits.get(0).getStones());
        assertEquals(5, pits.get(1).getStones());
        assertEquals(0, pits.get(2).getStones());
        assertEquals(5, pits.get(3).getStones());
        assertEquals(5, pits.get(4).getStones());
        assertEquals(0, pits.get(5).getStones());
    }

    @Test
    public void createPlayerPits() {
        ArrayList<Pit> pits = new ArrayList<>();
        PitUtils.createPlayerPits(1, 4, pits, 2);

        assertEquals(3, pits.size());
        assertEquals(2, pits.get(0).getStones());
        assertEquals(2, pits.get(1).getStones());
        assertEquals(2, pits.get(2).getStones());
    }

}