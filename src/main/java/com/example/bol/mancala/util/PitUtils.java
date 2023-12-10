package com.example.bol.mancala.util;

import com.example.bol.mancala.entity.Pit;

import java.util.ArrayList;
import java.util.stream.IntStream;

public final class PitUtils {

    private static final int FIRST_PIT_NUMBER = 1;
    private static final int EXCLUSIVE_OFFSET = 1;
    private static final int SECOND_PLAYER_PIT_MULTIPLIER = 2;

    public static void clear(Pit pit) {
        pit.setStones(0);
    }

    public static void addStone(Pit pit) {
        addStones(pit, 1);
    }

    public static void addStones(Pit pit, int stones) {
        pit.setStones(pit.getStones() + stones);
    }


    public static ArrayList<Pit> createPits(int pitsAmount, int stones) {
        ArrayList<Pit> playersPits = new ArrayList<>();
        int housePitNumberPlayerA = pitsAmount + EXCLUSIVE_OFFSET;

        int lastPitNumberPlayerB = housePitNumberPlayerA * SECOND_PLAYER_PIT_MULTIPLIER;


        createPlayerPits(FIRST_PIT_NUMBER, pitsAmount + EXCLUSIVE_OFFSET, playersPits, stones);
        playersPits.add(Pit.createHousePit(housePitNumberPlayerA));
        createPlayerPits(housePitNumberPlayerA + EXCLUSIVE_OFFSET, lastPitNumberPlayerB, playersPits, stones);
        playersPits.add(Pit.createHousePit(lastPitNumberPlayerB));
        return playersPits;
    }

    public static void createPlayerPits(int startInclusive, int endExclusive, ArrayList<Pit> playersPits, int stones) {
        IntStream.range(startInclusive, endExclusive).forEach(number -> playersPits.add(new Pit(number, stones)));
    }
}
