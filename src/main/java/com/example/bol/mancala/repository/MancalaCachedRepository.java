package com.example.bol.mancala.repository;

import com.example.bol.mancala.entity.MancalaGame;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

import static com.example.bol.mancala.configuration.Constants.MANCALA_GAME_CACHE_NAME;

public interface MancalaCachedRepository extends CrudRepository<MancalaGame, UUID> {

    @EntityGraph(attributePaths = {"pits"})
    @Cacheable(value = MANCALA_GAME_CACHE_NAME, key = "#id")
    Optional<MancalaGame> findById(UUID id);

    @CachePut(value = MANCALA_GAME_CACHE_NAME, key = "#entity.id")
    MancalaGame save(MancalaGame entity);
}
