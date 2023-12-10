package com.example.bol.mancala.service;

import com.example.bol.mancala.dto.MancalaGameCreateDto;
import com.example.bol.mancala.entity.MancalaGame;
import com.example.bol.mancala.entity.MancalaGame.MancalaGameBuilder;
import com.example.bol.mancala.exception.MancalaGameNotFoundException;
import com.example.bol.mancala.repository.MancalaCachedRepository;
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

    private final MancalaCachedRepository mancalaCachedRepository;

    @Override
    public MancalaGame create(MancalaGameCreateDto dto) {
        MancalaGameBuilder builder = MancalaGame.builder()
                .pits(PitUtils.createPits(dto.getPitsAmount(), dto.getStones()));
        dto.getPlayerA().ifPresent(builder::playerA);
        dto.getPlayerB().ifPresent(builder::playerB);

        return mancalaCachedRepository.save(builder.build());
    }


    @Override
    public MancalaGame get(UUID gameId) {
        return getGame(gameId);
    }

    @Override
    public MancalaGame move(UUID gameId, String pit) {
        getGame(gameId);
        return null;
    }

    private MancalaGame getGame(UUID gameId) {
        Optional<MancalaGame> mancalaGameOptional = mancalaCachedRepository.findById(gameId);
        if (mancalaGameOptional.isEmpty()) {
            throw new MancalaGameNotFoundException(gameId);
        }
        return mancalaGameOptional.get();
    }
}
