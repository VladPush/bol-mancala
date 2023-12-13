package com.example.bol.mancala.test.helper.builder;

import com.example.bol.mancala.dto.enums.PlayerTurn;
import com.example.bol.mancala.dto.enums.PlayerType;
import com.example.bol.mancala.entity.MancalaGame;
import com.example.bol.mancala.entity.Pit;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.With;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor(staticName = "aGame")
@With
public class MancalaGameEntityBuilder implements TestBuilder<MancalaGame> {
    private PlayerTurn turn = PlayerTurn.PlayerA;
    private PlayerType playerA = PlayerType.HUMAN;
    private PlayerType playerB = PlayerType.HUMAN;
    private List<Pit> pits = List.of(new Pit(1, 2), new Pit(2, 2), new Pit(3, 2), new Pit(4, 0),
            new Pit(5, 2), new Pit(6, 2), new Pit(7, 2), new Pit(8, 0));


    @Override
    public MancalaGame build() {
        final MancalaGame mancalaGame = new MancalaGame();
        mancalaGame.setTurn(turn);
        mancalaGame.setPlayerA(playerA);
        mancalaGame.setPlayerB(playerB);
        mancalaGame.setPits(pits);
        return mancalaGame;
    }
}
