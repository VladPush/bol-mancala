package com.example.bol.mancala.entity;

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

    private PlayerTurn turn;

    @SuppressWarnings("JpaDataSourceORMInspection")
    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id", name = "game_id", nullable = false, updatable = false)
    private List<Pit> pits = new ArrayList<>();

}
