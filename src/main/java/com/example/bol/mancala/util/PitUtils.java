package com.example.bol.mancala.util;

import com.example.bol.mancala.entity.Pit;
import com.example.bol.mancala.exception.MancalaRulesExceptionType;
import com.example.bol.mancala.exception.RulesExceptionFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public final class PitUtils {

    public static final int ZERO_STONES = 0;
    private static final int FIRST_PIT_NUMBER = 1;
    private static final int EXCLUSIVE_OFFSET = 1;
    private static final int SECOND_PLAYER_PIT_MULTIPLIER = 2;

    public static void clear(Pit pit) {
        pit.setStones(0);
    }


    public static boolean isEmpty(Pit pit) {
        return pit.getStones() == 0;
    }

    public static void addStone(Pit pit) {
        addStones(pit, 1);
    }

    public static void addStones(Pit pit, int stones) {
        pit.setStones(pit.getStones() + stones);
    }

    private static Pit createHousePit(int number) {
        return new Pit(number, 0);
    }

    public static Pit getPit(List<Pit> pits, int pitIndex) {
        try {
            return pits.get(pitIndex - 1);
        } catch (IndexOutOfBoundsException e) {
            throw RulesExceptionFactory.createException(MancalaRulesExceptionType.INCORRECT_PIT_NUMBER);
        }
    }

    public static ArrayList<Pit> createPits(int pitsAmount, int stones) {
        ArrayList<Pit> playersPits = new ArrayList<>();
        int housePitNumberPlayerA = pitsAmount + EXCLUSIVE_OFFSET;

        int lastPitNumberPlayerB = housePitNumberPlayerA * SECOND_PLAYER_PIT_MULTIPLIER;


        createPlayerPits(FIRST_PIT_NUMBER, pitsAmount + EXCLUSIVE_OFFSET, playersPits, stones);
        playersPits.add(createHousePit(housePitNumberPlayerA));
        createPlayerPits(housePitNumberPlayerA + EXCLUSIVE_OFFSET, lastPitNumberPlayerB, playersPits, stones);
        playersPits.add(createHousePit(lastPitNumberPlayerB));
        return playersPits;
    }

    public static void createPlayerPits(int startInclusive, int endExclusive, ArrayList<Pit> playersPits, int stones) {
        IntStream.range(startInclusive, endExclusive).forEach(number -> playersPits.add(new Pit(number, stones)));
    }
}
