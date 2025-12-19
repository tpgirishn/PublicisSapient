
package org.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "showtimes")
@Getter
@Setter
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

    @JsonIgnore
    @OneToMany(mappedBy = "showtime")
    private List<Booking> bookings;
}
