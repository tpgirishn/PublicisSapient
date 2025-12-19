
package org.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private String status;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "screen_id")
    private Screen screen;
}
