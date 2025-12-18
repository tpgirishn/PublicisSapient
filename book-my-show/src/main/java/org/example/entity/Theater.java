
package org.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "theaters")
@Getter
@Setter
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
