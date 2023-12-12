package com.example.bol.mancala.configuration;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

import static com.example.bol.mancala.configuration.Constants.CACHE_NAME_MANCALA_GAME;

@Configuration
public class CacheConfiguration {

    @Value("${mancala.cache.initial_capacity}")
    private int initialCapacity;

    @Value("${mancala.cache.maximum_size}")
    private int maximumSize;

    @Value("${mancala.cache.expire_after_write}")
    private int expireAfterWrite;

    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager(CACHE_NAME_MANCALA_GAME);
        cacheManager.setCaffeine(caffeineCacheBuilder());
        return cacheManager;
    }

    private Caffeine<Object, Object> caffeineCacheBuilder() {
        return Caffeine.newBuilder()
                .initialCapacity(initialCapacity)
                .maximumSize(maximumSize)
                .expireAfterWrite(Duration.ofMinutes(expireAfterWrite));
    }
}
