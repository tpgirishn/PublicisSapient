package org.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

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
    private BigDecimal price;
    @Column(name = "screen_id")
    private String screenId;
    @JsonIgnore
    @ManyToOne(optional = true)
    @JoinColumn(name = "screen_id", insertable = false, updatable = false)
    private Screen screen;
}
