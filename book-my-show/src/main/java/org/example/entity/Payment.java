package org.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "payments")
@Getter
@Setter
public class Payment {

    @Id
    private String id;

    private Instant paymentTime;
    private BigDecimal amount;
    private String paymentMethod;
    private String status;
    private String transactionId;
    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;
}
