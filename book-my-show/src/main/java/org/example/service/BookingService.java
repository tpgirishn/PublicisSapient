package org.example.service;

import org.example.entity.Booking;
import org.example.repository.BookingRepository;
import org.example.visitors.DiscountVisitor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class BookingService {
    Set<DiscountVisitor> visitors;
    BookingRepository bookingRepository;
    public BookingService(BookingRepository bookingRepository, Set<DiscountVisitor> visitors){
        this.bookingRepository = bookingRepository;
        this.visitors = visitors;
    }
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public Booking book(Booking booking){
        for (DiscountVisitor visitor: visitors){
            visitor.apply(booking);
        }
        return bookingRepository.saveAndFlush(booking);
    }

    public Booking getBooking(String id) {
        return bookingRepository.findById(id).get();
    }
}
