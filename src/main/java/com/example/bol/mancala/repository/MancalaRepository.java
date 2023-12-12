package com.example.bol.mancala.repository;

import com.example.bol.mancala.entity.MancalaGame;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MancalaRepository extends JpaRepository<MancalaGame, UUID> {

    @EntityGraph(attributePaths = {"pits"})
        //  @Cacheable(value = CACHE_NAME_MANCALA_GAME, key = "#id")
    Optional<MancalaGame> findById(UUID id);

    //   @CachePut(value = CACHE_NAME_MANCALA_GAME, key = "#entity.id")
    MancalaGame save(MancalaGame entity);
}
