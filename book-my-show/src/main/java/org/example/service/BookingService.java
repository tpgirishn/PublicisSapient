package org.example.service;

import org.example.entity.Booking;
import org.example.repository.BookingRepository;
import org.example.visitors.DiscountVisitor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class BookingService {
    Set<DiscountVisitor> visitors;
    BookingRepository bookingRepository;
    public BookingService(BookingRepository bookingRepository, Set<DiscountVisitor> visitors){
        this.bookingRepository = bookingRepository;
        this.visitors = visitors;
    }
    public Booking book(Booking booking){
        for (DiscountVisitor visitor: visitors){
            visitor.apply(booking);
        }
        bookingRepository.saveAndFlush(booking);
        return booking;
    }
}
