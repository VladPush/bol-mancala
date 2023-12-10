package com.example.bol.mancala.service;

import com.example.bol.mancala.dto.MancalaGameCreateDto;
import com.example.bol.mancala.entity.MancalaGame;
import com.example.bol.mancala.entity.MancalaGame.MancalaGameBuilder;
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
    public MancalaGame create(MancalaGameCreateDto dto) {
        MancalaGameBuilder builder = MancalaGame.builder()
                .pits(PitUtils.createPits(dto.pitsAmount(), dto.stones()));
        dto.playerA().ifPresent(builder::playerA);
        dto.playerB().ifPresent(builder::playerB);

        return mancalaRepository.save(builder.build());
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
