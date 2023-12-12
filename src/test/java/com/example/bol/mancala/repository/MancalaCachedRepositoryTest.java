package com.example.bol.mancala.repository;

import com.example.bol.mancala.AbstractIntegrationTest;
import com.example.bol.mancala.dto.enums.PlayerTurn;
import com.example.bol.mancala.dto.enums.PlayerType;
import com.example.bol.mancala.entity.MancalaGame;
import com.example.bol.mancala.entity.Pit;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class MancalaCachedRepositoryTest extends AbstractIntegrationTest {

    @SpyBean
    private MancalaCachedRepository mancalaCachedRepository;

    @Autowired
    private MancalaRepository mancalaRepository;

    @Test
    public void findByIdEager() {
        MancalaGame mancalaGame = MancalaGame.builder()
                .playerA(PlayerType.HUMAN)
                .playerB(PlayerType.HUMAN)
                .pits(List.of(new Pit(1, 2), new Pit(2, 2)))
                .build();

        mancalaCachedRepository.save(mancalaGame);

        Optional<MancalaGame> mancalaGameOptional = mancalaCachedRepository.findById(mancalaGame.getId());

        assertTrue(mancalaGameOptional.isPresent());
        assertEquals(mancalaGameOptional.get().getId(), mancalaGameOptional.get().getId());
        assertDoesNotThrow(() -> mancalaGameOptional.get().getPits().get(1));
    }

    @Test
    public void savePutInCache() {
        MancalaGame mancalaGame = MancalaGame.builder()
                .playerA(PlayerType.HUMAN)
                .playerB(PlayerType.HUMAN)
                .pits(new ArrayList<>())
                .build();

        mancalaCachedRepository.save(mancalaGame);

        Optional<MancalaGame> mancalaGameOptional = mancalaCachedRepository.findById(mancalaGame.getId());

        assertTrue(mancalaGameOptional.isPresent());
        verify(mancalaCachedRepository, times(0)).findById(mancalaGame.getId());
    }

    @Test
    public void saveChangeValueInCache() {
        MancalaGame mancalaGame = MancalaGame.builder()
                .playerA(PlayerType.HUMAN)
                .playerB(PlayerType.HUMAN)
                .pits(new ArrayList<>())
                .build();
        mancalaRepository.saveAndFlush(mancalaGame);
        Optional<MancalaGame> originalGameOprional = mancalaCachedRepository.findById(mancalaGame.getId());

        assertTrue(originalGameOprional.isPresent());
        verify(mancalaCachedRepository, times(1)).findById(mancalaGame.getId());

        MancalaGame originalGame = originalGameOprional.get();
        originalGame.setTurn(PlayerTurn.PlayerB);
        mancalaCachedRepository.save(originalGame);
        Optional<MancalaGame> changedGame = mancalaCachedRepository.findById(mancalaGame.getId());

        verify(mancalaCachedRepository, times(1)).findById(mancalaGame.getId());
        assertEquals(changedGame.get().getTurn(), PlayerTurn.PlayerB);
    }

    @Test
    public void findByIdPutInCache() {
        MancalaGame mancalaGame = MancalaGame.builder()
                .playerA(PlayerType.HUMAN)
                .playerB(PlayerType.HUMAN)
                .pits(new ArrayList<>())
                .build();

        mancalaRepository.saveAndFlush(mancalaGame);

        Optional<MancalaGame> mancalaGameOptionalFirstRequest = mancalaCachedRepository.findById(mancalaGame.getId());

        assertTrue(mancalaGameOptionalFirstRequest.isPresent());
        verify(mancalaCachedRepository, times(1)).findById(mancalaGame.getId());

        Optional<MancalaGame> mancalaGameOptionalSecondRequest = mancalaCachedRepository.findById(mancalaGame.getId());

        assertTrue(mancalaGameOptionalSecondRequest.isPresent());
        verify(mancalaCachedRepository, times(1)).findById(mancalaGame.getId());
    }

}