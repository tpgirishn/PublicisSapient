
package org.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "seats")
@Getter
@Setter
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
