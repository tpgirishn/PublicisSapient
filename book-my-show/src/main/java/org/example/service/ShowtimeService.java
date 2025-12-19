package org.example.service;

import org.example.entity.Showtime;
import org.example.repository.ShowtimeRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
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

    public Showtime createShowTime(Showtime showtime) {
        return showtimeRepository.saveAndFlush(showtime);
    }

    public Showtime updateShowTime(Showtime showtime) {
         if(showtimeRepository.existsById(showtime.getId())){
             return showtimeRepository.saveAndFlush(showtime);
         }else
             throw new RuntimeException("Show cannot be updated since it does not exist!");
    }

    public void deleteShowTime(String showId) {
        if(showtimeRepository.existsById(showId)){
            showtimeRepository.deleteById(showId);
        }else
            throw new RuntimeException("Show cannot be updated since it does not exist!");
    }
}
