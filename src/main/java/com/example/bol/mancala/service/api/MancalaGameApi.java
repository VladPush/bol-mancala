package com.example.bol.mancala.service.api;

import com.example.bol.mancala.entity.MancalaGame;
import com.example.bol.mancala.exception.MancalaGameNotFoundException;

import java.util.Optional;
import java.util.UUID;

public interface MancalaGameApi {
    MancalaGame create(int pits, int stones);

    Optional<MancalaGame> get(UUID gameId) throws MancalaGameNotFoundException;

}
