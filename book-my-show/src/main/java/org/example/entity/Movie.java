
package org.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "movies")
@Getter
@Setter
public class Movie {

    @Id
    private String id;

    private String title;
    private String description;
    private int durationMinutes;
    private String genre;
    private LocalDate releaseDate;
    private String rating;
    private String language;

    @OneToMany(mappedBy = "movie")
    private List<Review> reviews;

    @OneToMany(mappedBy = "movie")
    private List<Showtime> showtimes;
}
