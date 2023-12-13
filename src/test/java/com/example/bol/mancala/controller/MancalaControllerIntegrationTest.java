package com.example.bol.mancala.controller;

import com.example.bol.mancala.AbstractIntegrationTest;
import com.example.bol.mancala.dto.MancalaGameCreateDto;
import com.example.bol.mancala.dto.enums.PlayerType;
import com.example.bol.mancala.entity.MancalaGame;
import com.example.bol.mancala.exception.MancalaGameNotFoundException;
import com.example.bol.mancala.repository.MancalaRepository;
import com.example.bol.mancala.test.helper.builder.MancalaGameEntityBuilder;
import com.example.bol.mancala.util.PitUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;


public class MancalaControllerIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private MancalaController controller;

    @Autowired
    private MancalaRepository mancalaRepository;

    @Test
    public void createValid() {
        MancalaGameCreateDto validDto = new MancalaGameCreateDto(6, 4, Optional.of(PlayerType.HUMAN), Optional.of(PlayerType.HUMAN));

        MancalaGame mancalaGame = controller.create(validDto);
        assertNotNull(mancalaGame.getId());
        assertEquals(14, mancalaGame.getPits().size());
    }

    @Test
    public void getFound() {
        MancalaGame mancalaGame = MancalaGameEntityBuilder.aGame().withPits(PitUtils.createPits(8, 2))
                .build();

        UUID id = mancalaRepository.save(mancalaGame).getId();

        assertNotNull(controller.get(id));
    }

    @Test
    public void getNotFound() {
        UUID nonexistentId = UUID.randomUUID();

        MancalaGameNotFoundException mancalaGameNotFoundException = assertThrows(MancalaGameNotFoundException.class, () -> controller.get(nonexistentId));

        assertEquals("Mancala game with id " + nonexistentId + " does not exist", mancalaGameNotFoundException.getMessage());
    }

}