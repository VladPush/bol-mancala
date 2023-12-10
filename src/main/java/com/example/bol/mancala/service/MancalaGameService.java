package com.example.bol.mancala.service;

import com.example.bol.mancala.dto.MancalaGameCreateDto;
import com.example.bol.mancala.dto.enums.PlayerTurn;
import com.example.bol.mancala.dto.enums.PlayerType;
import com.example.bol.mancala.entity.MancalaGame;
import com.example.bol.mancala.entity.MancalaGame.MancalaGameBuilder;
import com.example.bol.mancala.exception.MancalaGameNotFoundException;
import com.example.bol.mancala.repository.MancalaCachedRepository;
import com.example.bol.mancala.service.api.MancalaGameApi;
import com.example.bol.mancala.service.move.MoveStrategy;
import com.example.bol.mancala.service.move.MoveStrategyBuilder;
import com.example.bol.mancala.util.PitUtils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MancalaGameService {

    private final MancalaCachedRepository mancalaCachedRepository;
    private final MancalaGameApi mancalaGameApi;

    public MancalaGame create(MancalaGameCreateDto dto) {
        MancalaGameBuilder builder = MancalaGame.builder()
                .pits(PitUtils.createPits(dto.getPitsAmount(), dto.getStones()));
        dto.getPlayerA().ifPresent(builder::playerA);
        dto.getPlayerB().ifPresent(builder::playerB);

        return mancalaCachedRepository.save(builder.build());
    }


    public MancalaGame get(UUID gameId) {
        return getGame(gameId);
    }

    @SneakyThrows
    public MancalaGame move(UUID gameId, Integer pit) {
        MancalaGame game = getGame(gameId);
        MoveStrategy moveStrategy = getMoveStrategy(game, pit);

        PlayerTurn currentTurn = game.getTurn();
        int pitForMove = moveStrategy.getPitForMove(game.getPits(), currentTurn);

        MancalaGameApi.MovedMancalaGameDto movedGame = mancalaGameApi.move(game.getPits(), pitForMove);

        game.setPits(movedGame.getPits());
        game.setTurn(movedGame.getNextTurn());

        return mancalaCachedRepository.save(game);
    }

    private MoveStrategy getMoveStrategy(MancalaGame game, Integer pit) {
        PlayerType playerType = getPlayerTypeForCurrentMove(game);
        MoveStrategyBuilder.Components strategyComponents = MoveStrategyBuilder.Components
                .builder()
                .pit(pit)
                .build();
        return MoveStrategyBuilder.build(playerType, strategyComponents);
    }


    private PlayerType getPlayerTypeForCurrentMove(MancalaGame game) {
        PlayerTurn turn = game.getTurn();
        return (turn == null || PlayerTurn.PlayerA == turn) ? game.getPlayerA() : game.getPlayerB();
    }

    private MancalaGame getGame(UUID gameId) {
        Optional<MancalaGame> mancalaGameOptional = mancalaCachedRepository.findById(gameId);
        if (mancalaGameOptional.isEmpty()) {
            throw new MancalaGameNotFoundException(gameId);
        }
        return mancalaGameOptional.get();
    }
}
