package com.example.bol.mancala.service;

import com.example.bol.mancala.AbstractIntegrationTest;
import com.example.bol.mancala.entity.MancalaGame;
import com.example.bol.mancala.entity.Pit;
import com.example.bol.mancala.repository.MancalaRepository;
import com.example.bol.mancala.test.helper.builder.MancalaGameEntityBuilder;
import com.example.bol.mancala.util.PitUtils;
import jdk.jshell.spi.ExecutionControl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MancalaGameServiceIntegrationTest extends AbstractIntegrationTest {


    @Autowired
    private MancalaGameService mancalaGameService;

    @Autowired
    private MancalaRepository mancalaRepository;


    @Test
    public void moveSimple() throws ExecutionControl.NotImplementedException {
        MancalaGame mancalaGame = mancalaRepository.save(MancalaGameEntityBuilder.aGame().withPits(PitUtils.createPits(3, 2)).build());

        mancalaGameService.move(mancalaGame.getId(), 1);

        MancalaGame mancalaGameAfterMove = mancalaRepository.findById(mancalaGame.getId()).get();
        Map<Integer, List<Integer>> groupedByStoneAmount = mancalaGameAfterMove.getPits().stream().collect(groupingBy(Pit::getStones, mapping(Pit::getNumber, toList())));

        assertTrue(groupedByStoneAmount.get(0).containsAll(List.of(1, 4, 8)));
        assertTrue(groupedByStoneAmount.get(2).containsAll(List.of(5, 6, 7)));
        assertTrue(groupedByStoneAmount.get(3).containsAll(List.of(2, 3)));
    }


}