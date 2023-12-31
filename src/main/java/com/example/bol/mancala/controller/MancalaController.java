package com.example.bol.mancala.controller;

import com.example.bol.mancala.controller.api.MancalaControllerApi;
import com.example.bol.mancala.dto.MancalaGameCreateDto;
import com.example.bol.mancala.entity.MancalaGame;
import com.example.bol.mancala.service.MancalaGameService;
import jakarta.validation.Valid;
import jdk.jshell.spi.ExecutionControl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/game/mancala")
@RequiredArgsConstructor
public class MancalaController implements MancalaControllerApi {

    private final MancalaGameService gameService;

    @Override
    @PostMapping
    public MancalaGame create(@RequestBody @Valid MancalaGameCreateDto dto) {
        return gameService.create(dto);
    }

    @Override
    @GetMapping("/{gameId}")
    public MancalaGame get(@PathVariable final UUID gameId) {
        return gameService.get(gameId);
    }

    @Override
    @PutMapping("/{gameId}/move")
    public MancalaGame move(@PathVariable UUID gameId, @RequestParam(required = false) Integer pit) throws ExecutionControl.NotImplementedException {
        return gameService.move(gameId, pit);
    }

}
