package com.example.bol.mancala.service;

import com.example.bol.mancala.entity.MancalaGame;
import com.example.bol.mancala.repository.MancalaRepository;
import com.example.bol.mancala.service.api.MancalaGameApi;
import com.example.bol.mancala.service.api.MancalaMoveApi;
import com.example.bol.mancala.util.PitUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MancalaGameService implements MancalaGameApi, MancalaMoveApi {

    private final MancalaRepository mancalaRepository;

    @Override
    public MancalaGame create(int pitsAmount, int stones) {
        MancalaGame build = MancalaGame.builder()
                .pits(PitUtils.createPits(pitsAmount, stones))
                .build();

        return mancalaRepository.save(build);
    }


    @Override
    public Optional<MancalaGame> get(UUID gameId) {
        return mancalaRepository.findById(gameId);
    }

    @Override
    public MancalaGame move(UUID gameId, String pit) {
        return null;
    }
}
