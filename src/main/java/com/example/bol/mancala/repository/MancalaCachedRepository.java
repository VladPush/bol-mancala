package com.example.bol.mancala.repository;

import com.example.bol.mancala.entity.MancalaGame;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

import static com.example.bol.mancala.configuration.Constants.CACHE_NAME_MANCALA_GAME;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = CACHE_NAME_MANCALA_GAME)
public class MancalaCachedRepository {

    private final MancalaRepository mancalaRepository;

    @Cacheable(key = "#id")
    public Optional<MancalaGame> findById(UUID id) {
        return mancalaRepository.findById(id);
    }

    @CachePut(key = "#entity.id")
    public MancalaGame save(MancalaGame entity) {
        return mancalaRepository.save(entity);
    }
}
