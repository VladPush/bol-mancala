package com.example.bol.mancala.service.api;

import com.example.bol.mancala.entity.MancalaGame;

@FunctionalInterface
public interface MancalaGameApi {
    MancalaGame move(MancalaGame game, Integer pit);
}
