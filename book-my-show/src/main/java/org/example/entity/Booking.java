
package com.example.moviebooking.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    private String id;

    private Instant bookingTime;
    private BigDecimal totalAmount;
    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "showtime_id")
    private Showtime showtime;

    @ManyToOne
    @JoinColumn(name = "promotion_id")
    private Promotion promotion;

    @OneToMany(mappedBy = "booking")
    private List<Payment> payments;

    @OneToMany(mappedBy = "booking")
    private List<BookingSeat> bookingSeats;
}
