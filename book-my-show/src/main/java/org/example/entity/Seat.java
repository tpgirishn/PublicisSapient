
package com.example.moviebooking.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "seats")
public class Seat {

    @Id
    private String id;

    private String seatNumber;
    private String seatType;
    private boolean isVip;

    @ManyToOne
    @JoinColumn(name = "screen_id")
    private Screen screen;
}
