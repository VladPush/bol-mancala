package com.example.bol.mancala.entity;

import com.example.bol.mancala.dto.enums.PlayerTurn;
import com.example.bol.mancala.dto.enums.PlayerType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "mancala")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class MancalaGame {

    @Id
    @UuidGenerator
    private UUID id;

    @Enumerated(EnumType.STRING)
    private PlayerTurn turn;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private PlayerType playerA = PlayerType.HUMAN;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private PlayerType playerB = PlayerType.HUMAN;

    @SuppressWarnings("JpaDataSourceORMInspection")
    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id", name = "game_id", nullable = false, updatable = false)
    private List<Pit> pits = new ArrayList<>();

}
