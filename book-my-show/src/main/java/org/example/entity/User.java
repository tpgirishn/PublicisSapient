
package com.example.moviebooking.entity;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    private String id;

    private String name;
    private String email;
    private String passwordHash;
    private String phone;
    private Instant createdAt;

    @OneToMany(mappedBy = "user")
    private List<Review> reviews;

    @OneToMany(mappedBy = "user")
    private List<Booking> bookings;
}
