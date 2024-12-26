package com.ali.hunter.service.impl;


import com.ali.hunter.domain.entity.Booking;
import com.ali.hunter.domain.enums.BookingStatus;
import com.ali.hunter.exception.ResourceNotFoundException;
import com.ali.hunter.repository.BookingRepository;
import com.ali.hunter.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl  implements BookingService{
    private final BookingRepository bookingRepository;

    @Override
    public Page<Booking> searchBookings(Booking booking, Pageable pageable) {
        ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example<Booking> example = Example.of(booking, matcher);
        return bookingRepository.findAll(example, pageable);
    }

    @Override
    public Booking createBooking(Booking booking) {
        booking.setBookingStatus(BookingStatus.PENDING);
        return bookingRepository.save(booking);
    }

    @Override
    public Booking updateBookingStatus(UUID id, BookingStatus status) {
        Booking existingBooking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));

        existingBooking.setBookingStatus(status);
        return bookingRepository.save(existingBooking);
    }

    @Override
    public Optional<Booking> findById(UUID id) {
        return bookingRepository.findById(id);
    }

    @Override
    public Page<Booking> findByUserId(UUID userId, Pageable pageable) {
        return bookingRepository.findByUserId(userId, pageable);
    }

    @Override
    public Page<Booking> findByVehicleId(UUID vehicleId, Pageable pageable) {
        return bookingRepository.findByVehicleId(vehicleId, pageable);
    }
}
