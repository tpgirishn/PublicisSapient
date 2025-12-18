
package com.example.moviebooking.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "promotions")
public class Promotion {

    @Id
    private String id;

    private String code;
    private String description;
    private int discountPercent;
    private LocalDate validFrom;
    private LocalDate validTo;
    private int maxUses;

    @OneToMany(mappedBy = "promotion")
    private List<Booking> bookings;
}
