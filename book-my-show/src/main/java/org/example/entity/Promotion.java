package org.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "promotions")
@Getter
@Setter
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    private String description;
    private int discountPercent;
    private LocalDate validFrom;
    private LocalDate validTo;
    private int maxUses;
    @JsonIgnore
    @OneToMany(mappedBy = "promotion")
    private List<Booking> bookings;
}
