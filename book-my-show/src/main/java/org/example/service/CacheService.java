package org.example.service;

import org.example.entity.Seat;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

@Service

public class CacheService {
    @CachePut(value = "seats", key = "#seat.id", condition = "#seat.status == 'AVAILABLE'")
    public Seat performSingleCachePut(Seat seat) {
        return seat;
    }

    @CacheEvict(value = "seats", key = "#seat.id")
    public Seat performSingleCacheEvict(Seat seat) {
        return seat;
    }
}
