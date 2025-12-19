package org.example.service;

import org.example.entity.Booking;
import org.example.repository.BookingRepository;
import org.example.repository.SeatRepository;
import org.example.visitors.DiscountVisitor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookingService {
    Set<DiscountVisitor> visitors;
    BookingRepository bookingRepository;
    SeatRepository seatRepository;
    public BookingService(BookingRepository bookingRepository, SeatRepository seatRepository, Set<DiscountVisitor> visitors){
        this.bookingRepository = bookingRepository;
        this.seatRepository = seatRepository;
        this.visitors = visitors;
    }
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public Booking book(Booking booking) throws CloneNotSupportedException {
        Set<String> seatsLockedOrAlreadyBooked = booking.getBookingSeats()
                .parallelStream()
                .map(mapper -> mapper.getSeat().getId()).collect(Collectors.toSet());
        Set<String> seatsForCurrentBooking = seatRepository.findAllById(seatsLockedOrAlreadyBooked)
                .parallelStream()
                .map(mapper -> mapper.getStatus()).collect(Collectors.toSet());

        if(seatsForCurrentBooking.contains("BOOKED")
                || seatsForCurrentBooking.contains("LOCKED")) {
            throw new RuntimeException("The current combination of seats are not available. " +
                    "Please select different seat combination.");
        }

        for (DiscountVisitor visitor: visitors){
            visitor.apply(booking);
        }
        booking.getBookingSeats().parallelStream().forEach(bookingSeat -> bookingSeat.getSeat().setStatus("BOOKED"));
        return bookingRepository.saveAndFlush(booking);
    }

    public Booking getBooking(String id) {
        return bookingRepository.findById(id).get();
    }
}
