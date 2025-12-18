
package com.example.moviebooking.entity;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "showtimes")
public class Showtime {

    @Id
    private String id;

    private Instant startTime;
    private Instant endTime;
    private String language;
    private String format;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "screen_id")
    private Screen screen;

    @OneToMany(mappedBy = "showtime")
    private List<Booking> bookings;
}
