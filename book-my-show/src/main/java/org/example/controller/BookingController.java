package org.example.controller;

import org.example.dto.BookingRequest;
import org.example.entity.Booking;
import org.example.service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
public class BookingController {
    BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/ticket")
    public ResponseEntity<Booking> getBooking(@RequestParam("id") String id) {
        return ResponseEntity.ok(bookingService.getBooking(id));
    }

    @PostMapping("/ticket")
    public ResponseEntity<Booking> bookTicket(@RequestBody BookingRequest bookingDTO) throws CloneNotSupportedException, ExecutionException, InterruptedException {
        return ResponseEntity.ok(bookingService.book(bookingDTO));
    }

}
