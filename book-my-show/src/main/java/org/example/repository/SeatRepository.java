package org.example.repository;

import org.example.entity.Seat;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
    default <S extends Seat> List<S> saveAll(Iterable<S> entities) {
        return (List<S>) this.saveAllAndFlush(entities);
    }


    default <S extends Seat> List<S> customFindAllById(Set<Long> seatsLockedOrAlreadyBooked) {
        return (List<S>) seatsLockedOrAlreadyBooked.
                parallelStream().map(mapper -> this.customFindById(mapper)).collect(Collectors.toList());
    }

    @Cacheable(value = "seats", key = "#id", condition = "#seat.id == 'AVAILABLE'")
    default <S extends Seat> Seat customFindById(Long id){
        return this.findById(id).get();
    }
    default void performCacheEvictforAll(List<Seat> savedSeats) {
        for (Seat seat : savedSeats) {
            performSingleCacheEvict(seat);
        }
    }

    @CacheEvict(value = "seats", key = "#seat.id")
    default void performSingleCacheEvict(Seat seat) {

    }
}
