package org.example.controller;

import org.example.entity.Showtime;
import org.example.entity.Theater;
import org.example.service.ShowtimeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class ShowtimeController {
    ShowtimeService showtimeService;
    public ShowtimeController(ShowtimeService showtimeService){
        this.showtimeService = showtimeService;
    }

    @GetMapping("/showtimes")
    public ResponseEntity<List<Showtime>> getBooking(@RequestParam("movieId") String movieId, @RequestParam("movieDate") LocalDate movieDate) {
        return ResponseEntity.ok(showtimeService.getTheatres(movieId,movieDate));
    }
}
