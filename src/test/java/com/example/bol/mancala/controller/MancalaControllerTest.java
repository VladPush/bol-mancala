package com.example.bol.mancala.controller;

import com.example.bol.mancala.dto.MancalaGameCreateDto;
import com.example.bol.mancala.dto.enums.PlayerType;
import com.example.bol.mancala.exception.MancalaGameNotFoundException;
import com.example.bol.mancala.service.MancalaGameService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MancalaController.class)
public class MancalaControllerTest {

    @MockBean
    private MancalaGameService gameService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void createValidMancalaGame() throws Exception {
        MancalaGameCreateDto validDto = new MancalaGameCreateDto(6, 4, Optional.of(PlayerType.HUMAN), Optional.of(PlayerType.HUMAN));

        mockMvc.perform(post("/game/mancala")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(validDto))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void createInvalidMancalaGameNegativeStones() throws Exception {
        MancalaGameCreateDto validDto = new MancalaGameCreateDto(6, -4, Optional.of(PlayerType.HUMAN), Optional.of(PlayerType.HUMAN));

        Exception resolvedException = mockMvc.perform(post("/game/mancala")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(validDto))
                        .accept(MediaType.APPLICATION_JSON))

                .andExpect(status().is4xxClientError())
                .andReturn().getResolvedException();

        assertTrue(resolvedException.getMessage().contains("must be greater than 0"));
    }

    @Test
    public void createInvalidMancalaGameSmallPitAmount() throws Exception {
        MancalaGameCreateDto validDto = new MancalaGameCreateDto(3, 4, Optional.of(PlayerType.HUMAN), Optional.of(PlayerType.HUMAN));

        Exception resolvedException = mockMvc.perform(post("/game/mancala")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(validDto))
                        .accept(MediaType.APPLICATION_JSON))

                .andExpect(status().is4xxClientError())
                .andReturn().getResolvedException();

        assertTrue(resolvedException.getMessage().contains("Pits amount must be more than or equal to 6."));
    }

    @Test
    public void createInvalidMancalaGameBigPitAmount() throws Exception {
        MancalaGameCreateDto validDto = new MancalaGameCreateDto(15, 4, Optional.of(PlayerType.HUMAN), Optional.of(PlayerType.HUMAN));

        Exception resolvedException = mockMvc.perform(post("/game/mancala")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(validDto))
                        .accept(MediaType.APPLICATION_JSON))

                .andExpect(status().is4xxClientError())
                .andReturn().getResolvedException();

        assertTrue(resolvedException.getMessage().contains("Pits amount must be less than or equal to 10."));
    }

    @Test
    public void getNonExistentMancalaGameReturnException() throws Exception {
        UUID nonexistentId = UUID.randomUUID();
        MancalaGameNotFoundException mancalaGameNotFoundException = new MancalaGameNotFoundException(nonexistentId);
        when(gameService.get(nonexistentId)).thenThrow(mancalaGameNotFoundException);
        Exception resolvedException = mockMvc.perform(get("/game/mancala/" + nonexistentId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))

                .andExpect(status().is4xxClientError())
                .andReturn().getResolvedException();

        assertTrue(resolvedException.getMessage().contains(mancalaGameNotFoundException.getMessage()));
    }
}