package org.example.service;

import org.example.entity.Seat;
import org.example.entity.Showtime;
import org.example.repository.ShowtimeRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShowtimeService {
    ShowtimeRepository showtimeRepository;
    CacheService cacheService;

    public ShowtimeService(ShowtimeRepository showtimeRepository, CacheService cacheService) {
        this.showtimeRepository = showtimeRepository;
        this.cacheService = cacheService;
    }

    public List<Showtime> getTheatres(String movieId, LocalDate movieDate, String town) {
        Instant movieStartInstant = movieDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
        Instant movieEndInstant = movieDate.atStartOfDay(ZoneId.systemDefault()).toInstant().plus(24, ChronoUnit.HOURS);
        return showtimeRepository.findAllByMovieAndDateAndTown(movieId, movieStartInstant, movieEndInstant, town);
    }

    public List<Showtime> createShowTime(List<Showtime> showtimes) {
        List<Showtime> savedShowtimes = showtimeRepository.saveAll(showtimes);
        return savedShowtimes;
    }

    public void performCachePutforAll(List<Seat> savedSeats) {
        for (Seat seat : savedSeats) {
            cacheService.performSingleCachePut(seat);
        }
    }

    public void performCacheEvictforAll(List<Seat> savedSeats) {
        for (Seat seat : savedSeats) {
            cacheService.performSingleCacheEvict(seat);
        }
    }

    public Showtime updateShowTime(Showtime showtime) {
        if (showtimeRepository.existsById(showtime.getId())) {
            if (showtime.getScreen().getSeats() != null) {
                showtime.getScreen().getSeats().parallelStream()
                        .map(cacheService::performSingleCachePut).findAny();
            }
            return showtimeRepository.saveAndFlush(showtime);
        } else
            throw new RuntimeException("Show cannot be updated since it does not exist!");
    }

    public void deleteShowTime(List<Showtime> showtimes) {
        showtimeRepository.deleteAll(showtimes);
        performCacheEvictforAll(showtimes.parallelStream()
                .map(mapper -> mapper.getScreen().getSeats())
                .flatMap(mapper1 -> mapper1.stream().distinct())
                .collect(Collectors.toList()));
    }
}
