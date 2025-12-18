
package com.example.moviebooking.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "screens")
public class Screen {

    @Id
    private String id;

    private String name;
    private int totalSeats;

    @ManyToOne
    @JoinColumn(name = "theater_id")
    private Theater theater;

    @OneToMany(mappedBy = "screen")
    private List<Seat> seats;

    @OneToMany(mappedBy = "screen")
    private List<Showtime> showtimes;
}
