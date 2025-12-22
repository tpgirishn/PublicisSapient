package org.example.controller;

import org.example.entity.Showtime;
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
    public ResponseEntity<List<Showtime>> getShowTimes(@RequestParam("movieId") String movieId, @RequestParam("movieDate") LocalDate movieDate) {
        return ResponseEntity.ok(showtimeService.getTheatres(movieId,movieDate));
    }

    @PostMapping("/showtimes")
    public ResponseEntity<List<Showtime>> createShowTimes(@RequestBody List<Showtime> showtimes) {
        return ResponseEntity.ok(showtimeService.createShowTime(showtimes));
    }

    @PutMapping("/showtimes")
    public ResponseEntity<Showtime> updateShowTime(@RequestBody Showtime showtime) {
        return ResponseEntity.ok(showtimeService.updateShowTime(showtime));
    }

    @DeleteMapping("/showtimes")
    public ResponseEntity<String> deleteShowTime(@RequestBody List<Showtime> showtimes) {
        showtimeService.deleteShowTime(showtimes);
        return ResponseEntity.ok().body("Deleted successfully");
    }
}
