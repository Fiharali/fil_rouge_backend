package com.ali.hunter.service;


import com.ali.hunter.domain.entity.Booking;
import com.ali.hunter.domain.enums.BookingStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;
import java.util.UUID;


public interface BookingService {
    Page<Booking> searchBookings(Booking booking, Pageable pageable);
    Booking createBooking(Booking booking);
    Booking updateBookingStatus(UUID id, BookingStatus status);
    Optional<Booking> findById(UUID id);
    Page<Booking> findByUserId(UUID userId, Pageable pageable);
    Page<Booking> findByVehicleId(UUID vehicleId, Pageable pageable);
}
