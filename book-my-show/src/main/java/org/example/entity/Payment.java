
package com.example.moviebooking.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    private String id;

    private Instant paymentTime;
    private BigDecimal amount;
    private String paymentMethod;
    private String status;
    private String transactionId;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;
}
