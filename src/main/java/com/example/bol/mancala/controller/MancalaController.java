package com.example.bol.mancala.controller;

import com.example.bol.mancala.dto.MancalaGameCreateDto;
import com.example.bol.mancala.entity.MancalaGame;
import com.example.bol.mancala.exception.MancalaGameNotFoundException;
import com.example.bol.mancala.service.MancalaGameService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/game/mancala")
@RequiredArgsConstructor
public class MancalaController {

    private final MancalaGameService gameService;

    @PostMapping
    private MancalaGame create(@RequestBody MancalaGameCreateDto dto) {
        return gameService.create(dto);
    }

    @GetMapping("/{gameId}")
    private MancalaGame get(@PathVariable final UUID gameId) throws MancalaGameNotFoundException {
        Optional<MancalaGame> mancalaGameOptional = gameService.get(gameId);
        if (mancalaGameOptional.isEmpty()) {
            throw new MancalaGameNotFoundException(gameId);
        }
        return mancalaGameOptional.get();
    }

    @PutMapping("/{gameId}/move/{pit}")
    private MancalaGame move(@PathVariable UUID gameId, @PathVariable String pit) {
        return new MancalaGame();
    }

}
