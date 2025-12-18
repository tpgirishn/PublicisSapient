package org.example.repository;

import org.example.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public abstract class BookingRepository implements JpaRepository<Booking, String> {
}
