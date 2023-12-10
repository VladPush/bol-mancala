package com.example.bol.mancala.service.api;

import com.example.bol.mancala.entity.MancalaGame;

import java.util.UUID;

public interface MancalaMoveApi {
    MancalaGame move(UUID gameId, String pit);

}
