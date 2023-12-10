package com.example.bol.mancala.service.api;

import com.example.bol.mancala.dto.MancalaGameCreateDto;
import com.example.bol.mancala.entity.MancalaGame;

import java.util.UUID;

public interface MancalaGameApi {
    MancalaGame create(MancalaGameCreateDto dto);

    MancalaGame get(UUID gameId);

}
