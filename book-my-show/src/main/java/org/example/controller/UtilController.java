package org.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UtilController {
    @Autowired
    CacheManager cacheManager;

    @GetMapping("/cache")
    public ResponseEntity getCacheContents() {
        return ResponseEntity.ok(cacheManager.getCache("seats").getNativeCache());
    }
}
