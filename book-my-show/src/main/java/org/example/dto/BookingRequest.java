package org.example.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.entity.Seat;
import org.example.entity.Showtime;
import org.example.entity.User;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
public class BookingRequest {
    Set<Seat> bookingSeats;
    LocalDateTime bookingTime;
    Showtime showtime;
    User user;
}
