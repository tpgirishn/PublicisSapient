package org.example.service;

import org.example.entity.Showtime;
import org.example.entity.Theater;
import org.example.repository.ShowtimeRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.List;

@Service
public class ShowtimeService {
    ShowtimeRepository showtimeRepository;
    public ShowtimeService(ShowtimeRepository showtimeRepository){
        this.showtimeRepository = showtimeRepository;
    }
    public List<Showtime> getTheatres(String movieId, LocalDate movieDate) {
        Instant movieStartInstant = movieDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
        Instant movieEndInstant = movieDate.atStartOfDay(ZoneId.systemDefault()).toInstant().plus(24, ChronoUnit.HOURS);
        return showtimeRepository.findAllByMovieAndDate(movieId, movieStartInstant, movieEndInstant);
    }
}
