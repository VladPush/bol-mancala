package com.example.bol.mancala.service.api;

import com.example.bol.mancala.dto.MancalaGameCreateDto;
import com.example.bol.mancala.entity.MancalaGame;
import com.example.bol.mancala.exception.MancalaGameNotFoundException;

import java.util.Optional;
import java.util.UUID;

public interface MancalaGameApi {
    MancalaGame create(MancalaGameCreateDto dto);

    Optional<MancalaGame> get(UUID gameId) throws MancalaGameNotFoundException;

}
