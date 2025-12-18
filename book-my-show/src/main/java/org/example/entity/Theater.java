
package com.example.moviebooking.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "theaters")
public class Theater {

    @Id
    private String id;

    private String name;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private String phone;

    @OneToMany(mappedBy = "theater")
    private List<Screen> screens;
}
