package org.example.dtoToEntityConverters;

import org.example.dto.BookingRequest;
import org.example.entity.Booking;
import org.example.entity.BookingSeat;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.stream.Collectors;

@Component
public class BookingConverter {
    public Booking convertToBookingEntity(BookingRequest bookingRequest) {
        Booking booking = Booking.builder()
                .bookingTime(Instant.now())
                .user(bookingRequest.getUser())
                .showtime(bookingRequest.getShowtime())
                .bookingSeats(bookingRequest.getBookingSeats().parallelStream()
                        .map(mapper -> {
                            BookingSeat bookingSeat = new BookingSeat();
                            bookingSeat.setSeat(mapper);
                            bookingSeat.setPrice(mapper.getPrice());
                            return bookingSeat;
                        })
                        .collect(Collectors.toList())).build();
        booking.getBookingSeats().forEach(mapper -> mapper.setBooking(booking));
        return booking;
    }
}
