
package com.example.moviebooking.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "movies")
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
