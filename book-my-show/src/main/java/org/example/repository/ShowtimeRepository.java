package org.example.repository;

import org.example.entity.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface ShowtimeRepository extends JpaRepository<Showtime, String> {
    @Query("Select s from Showtime s  where s.movie.id = :movieId " +
            " AND s.startTime BETWEEN :movieStartInstant and :movieEndInstant")
    public List<Showtime> findAllByMovieAndDate(String movieId, Instant movieStartInstant, Instant movieEndInstant);

    default public <S extends Showtime> List<S> saveAll(Iterable<S> entities) {
        List<Showtime> savedShowtimes = (List<Showtime>) this.saveAllAndFlush(entities);
        return (List<S>) savedShowtimes;

    }
}
