
package org.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "screens")
@Getter
@Setter
public class Screen {

    @Id
    private String id;

    private String name;
    private int totalSeats;

    @ManyToOne
    @JoinColumn(name = "theater_id")
    private Theater theater;
    @JsonIgnore
    @OneToMany(mappedBy = "screen")
    private List<Seat> seats;
    @JsonIgnore
    @OneToMany(mappedBy = "screen")
    private List<Showtime> showtimes;
}
