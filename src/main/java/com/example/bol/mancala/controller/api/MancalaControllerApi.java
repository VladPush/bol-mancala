package com.example.bol.mancala.controller.api;

import com.example.bol.mancala.dto.MancalaGameCreateDto;
import com.example.bol.mancala.entity.MancalaGame;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jdk.jshell.spi.ExecutionControl;

import java.util.UUID;

@Tag(name = "Mancala", description = "Mancala game Api")
public interface MancalaControllerApi {
    @Operation(
            summary = "Creates new mancala game",
            description = "Creates new  entity from dto and save it in database, return new entity")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "400", description = "failed operation")
    })
    MancalaGame create(MancalaGameCreateDto dto);

    @Operation(
            summary = "Get mancala game",
            description = "Search entity by id, return entity if exist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "404", description = "failed operation")
    })
    MancalaGame get(UUID gameId);


    @Operation(
            summary = "Move mancala game",
            description = "Initiate move in game with uuid and from pit number from request, return updated entity")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "422", description = "failed operation")
    })
    MancalaGame move(UUID gameId, Integer pit) throws ExecutionControl.NotImplementedException;
}
