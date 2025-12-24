package org.example.controller;

import org.example.entity.Theater;
import org.example.repository.TheaterRepository;
import org.example.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {
    @Autowired
    CacheManager cacheManager;
    @Autowired
    TheaterRepository theaterRepository;
    @Autowired
    CacheService cacheService;

    @GetMapping("/cache")
    public ResponseEntity getCacheContents() {
        return ResponseEntity.ok(cacheManager.getCache("seats").getNativeCache());
    }

    @PostMapping("/theater")
    public ResponseEntity<Theater> saveSingleTheater(@RequestBody Theater theater) {
        theater.getScreens().forEach(eachScreen -> {
            eachScreen.getSeats().forEach(eachSeat -> eachSeat.setScreen(eachScreen));
            eachScreen.setTheater(theater);
        });
        Theater savedTheater = theaterRepository.saveAndFlush(theater);
        savedTheater.getScreens().forEach(eachScreen -> {
            eachScreen.getSeats().forEach(cacheService::performSingleCachePut);
        });
        return ResponseEntity.ok(savedTheater);
    }
}
