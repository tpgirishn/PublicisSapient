package org.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private int durationMinutes;
    private String genre;
    private LocalDate releaseDate;
    private String rating;
    private String language;
    @JsonIgnore
    @OneToMany(mappedBy = "movie")
    private List<Review> reviews;
    @JsonIgnore
    @OneToMany(mappedBy = "movie")
    private List<Showtime> showtimes;
}
