package com.example.bol.mancala.repository;

import com.example.bol.mancala.entity.MancalaGame;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface MancalaRepository extends CrudRepository<MancalaGame, UUID> {

    @EntityGraph(attributePaths = {"pits"})
    Optional<MancalaGame> findById(UUID id);

}
