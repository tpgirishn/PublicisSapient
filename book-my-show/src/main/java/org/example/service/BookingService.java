package org.example.service;

import org.example.entity.Booking;
import org.example.entity.Seat;
import org.example.repository.BookingRepository;
import org.example.repository.SeatRepository;
import org.example.visitors.DiscountVisitor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class BookingService {
    Set<DiscountVisitor> visitors;
    BookingRepository bookingRepository;
    SeatRepository seatRepository;
    CacheService cacheService;
    public BookingService(BookingRepository bookingRepository, SeatRepository seatRepository, CacheService cacheService, Set<DiscountVisitor> visitors){
        this.bookingRepository = bookingRepository;
        this.seatRepository = seatRepository;
        this.cacheService = cacheService;
        this.visitors = visitors;
    }
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public Booking book(Booking booking) throws CloneNotSupportedException, ExecutionException, InterruptedException {
        booking.setTotalAmount(booking.getBookingSeats().parallelStream()
                .map(mapper -> mapper.getPrice())
                .collect(Collectors
                        .reducing(new BigDecimal(0), BigDecimal::add)));
        Set<String> seatsLockedOrAlreadyBooked = booking.getBookingSeats()
                .parallelStream()
                .map(mapper -> mapper.getSeat().getId()).collect(Collectors.toSet());
        Set<String> seatsForCurrentBooking = seatRepository.customFindAllById(seatsLockedOrAlreadyBooked)
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
        List<Seat> seatsToBeBooked = booking.getBookingSeats()
                .parallelStream()
                .map(mapper->mapper.getSeat())
                .map(mapper1->{
                    mapper1.setStatus("LOCKED");
                    mapper1.setScreen(booking.getShowtime().getScreen());
                    cacheService.performSingleCacheEvict(mapper1);
                    return mapper1;
                })
                .collect(Collectors.toList());
        seatRepository.saveAll(seatsToBeBooked);
        CompletableFuture<String> paymentFuture = CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "Payment Successful";
        });
        if(paymentFuture.get().equalsIgnoreCase("Payment Successful")) {
            booking.setStatus("CONFIRMED");
            seatRepository.saveAll(booking.getBookingSeats()
                    .parallelStream()
                    .map(mapper->mapper.getSeat())
                    .map(mapper1->{
                        mapper1.setStatus("BOOKED");
                        mapper1.setScreen(booking.getShowtime().getScreen());
                        cacheService.performSingleCacheEvict(mapper1);
                        return mapper1;
                    })
                    .collect(Collectors.toList()));

            return bookingRepository.saveAndFlush(booking);
        } else {
            List<Seat> lockedSeats = booking.getBookingSeats()
                    .parallelStream()
                    .map(mapper->mapper.getSeat())
                    .map(mapper1->{
                        mapper1.setStatus("AVAILABLE");
                        cacheService.performSingleCachePut(mapper1);
                        return mapper1;
                    })
                    .collect(Collectors.toList());
            seatRepository.saveAll(lockedSeats);
            throw new RuntimeException("Payment Unsuccessful..");
        }
    }

    public Booking getBooking(String id) {
        return bookingRepository.findById(id).get();
    }
}
