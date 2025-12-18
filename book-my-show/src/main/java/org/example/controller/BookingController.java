package org.example.controller;

import org.example.entity.Booking;
import org.example.service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingController {
    BookingService bookingService;
    public BookingController(BookingService bookingService){
        this.bookingService = bookingService;
    }

    @PostMapping("/ticket")
    public ResponseEntity<Booking> bookTicket(@RequestBody Booking booking){
        return ResponseEntity.ok(bookingService.book(booking));
    }

}
