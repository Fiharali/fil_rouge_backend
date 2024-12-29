package com.ali.hunter.web.api.v1;

import com.ali.hunter.domain.entity.Booking;
import com.ali.hunter.domain.enums.BookingStatus;
import com.ali.hunter.exception.ResourceNotFoundException;
import com.ali.hunter.service.BookingService;
import com.ali.hunter.web.vm.booking.BookingMapper;
import com.ali.hunter.web.vm.booking.BookingRequest;
import com.ali.hunter.web.vm.booking.BookingResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;
    private final BookingMapper bookingMapper;

    @GetMapping
    public Page<BookingResponse> searchBookings(BookingRequest bookingRequest, Pageable pageable) {
        Booking booking = bookingMapper.toEntity(bookingRequest);
        return bookingService.searchBookings(booking, pageable)
                .map(bookingMapper::toResponse);
    }

    @PostMapping
    public BookingResponse createBooking(@Valid @RequestBody BookingRequest bookingRequest) {
        Booking booking = bookingMapper.toEntity(bookingRequest);
        Booking savedBooking = bookingService.createBooking(booking);
        return bookingMapper.toResponse(savedBooking);
    }

    @PutMapping("/{id}/status")
    public BookingResponse updateBookingStatus(@PathVariable UUID id, @RequestParam BookingStatus status) {
        Booking updatedBooking = bookingService.updateBookingStatus(id, status);
        return bookingMapper.toResponse(updatedBooking);
    }

    @GetMapping("/{id}")
    public BookingResponse getBookingById(@PathVariable UUID id) {
        return bookingService.findById(id)
                .map(bookingMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + id));
    }

    @GetMapping("/user/{userId}")
    public Page<BookingResponse> getBookingsByUserId(@PathVariable UUID userId, Pageable pageable) {
        return bookingService.findByUserId(userId, pageable)
                .map(bookingMapper::toResponse);
    }

    @GetMapping("/vehicle/{vehicleId}")
    public Page<BookingResponse> getBookingsByVehicleId(@PathVariable UUID vehicleId, Pageable pageable) {
        return bookingService.findByVehicleId(vehicleId, pageable)
                .map(bookingMapper::toResponse);
    }
}
