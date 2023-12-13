package com.example.bol.mancala.service;

import com.example.bol.mancala.entity.MancalaGame;
import com.example.bol.mancala.exception.MancalaGameRulesException;
import com.example.bol.mancala.repository.MancalaCachedRepository;
import com.example.bol.mancala.service.api.MancalaGameApi;
import com.example.bol.mancala.test.helper.builder.MancalaGameEntityBuilder;
import com.example.bol.mancala.util.PitUtils;
import jdk.jshell.spi.ExecutionControl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static com.example.bol.mancala.exception.MancalaRulesExceptionType.INCORRECT_PIT_NUMBER_OUT_OF_RANGE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MancalaGameServiceTest {


    @InjectMocks
    private MancalaGameService mancalaGameService;

    @Mock
    private MancalaCachedRepository mancalaRepository;
    @Mock
    private MancalaGameApi mancalaGameApi;

    @Test
    public void moveValidPit() throws ExecutionControl.NotImplementedException {

        Optional<MancalaGame> mancalaGameOptional = Optional.of(MancalaGameEntityBuilder.aGame().withPits(PitUtils.createPits(3, 2)).build());

        when(mancalaRepository.findById(any())).thenReturn(mancalaGameOptional);
        when(mancalaRepository.save(any())).thenReturn(mancalaGameOptional.get());

        assertNotNull(mancalaGameService.move(UUID.randomUUID(), 2));
    }

    @Test
    public void moveZeroPit() {

        when(mancalaRepository.findById(any())).thenReturn(Optional.of(MancalaGameEntityBuilder.aGame().withPits(PitUtils.createPits(3, 2)).build()));

        MancalaGameRulesException mancalaGameRulesException = assertThrows(MancalaGameRulesException.class, () -> mancalaGameService.move(UUID.randomUUID(), 0));
        assertEquals(mancalaGameRulesException.getMessage(), INCORRECT_PIT_NUMBER_OUT_OF_RANGE.getMessage());
    }

    @Test
    public void moveNegativePit() {

        when(mancalaRepository.findById(any())).thenReturn(Optional.of(MancalaGameEntityBuilder.aGame().withPits(PitUtils.createPits(3, 2)).build()));

        MancalaGameRulesException mancalaGameRulesException = assertThrows(MancalaGameRulesException.class, () -> mancalaGameService.move(UUID.randomUUID(), -1));
        assertEquals(mancalaGameRulesException.getMessage(), INCORRECT_PIT_NUMBER_OUT_OF_RANGE.getMessage());
    }

    @Test
    public void moveOutOfRangePit() {
        int pitsAmount = 3;
        when(mancalaRepository.findById(any())).thenReturn(Optional.of(MancalaGameEntityBuilder.aGame().withPits(PitUtils.createPits(pitsAmount, 2)).build()));

        MancalaGameRulesException mancalaGameRulesException = assertThrows(MancalaGameRulesException.class, () -> mancalaGameService.move(UUID.randomUUID(), pitsAmount * 5));
        assertEquals(mancalaGameRulesException.getMessage(), INCORRECT_PIT_NUMBER_OUT_OF_RANGE.getMessage());
    }


}