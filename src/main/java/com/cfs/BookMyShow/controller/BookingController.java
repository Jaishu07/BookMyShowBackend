package com.cfs.BookMyShow.controller;

import com.cfs.BookMyShow.dto.BookingDto;
import com.cfs.BookMyShow.dto.BookingRequestDto;
import com.cfs.BookMyShow.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {


    private final BookingService bookingService;

    // Constructor Injection (Best Practice)
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    //  Create Booking
    @PostMapping
    public ResponseEntity<BookingDto> createBooking(@Valid @RequestBody BookingRequestDto bookingRequest) {
        BookingDto booking = bookingService.createBooking(bookingRequest);
        return ResponseEntity.status(201).body(booking);
    }

    //  Get Booking by ID
    @GetMapping("/{id}")
    public ResponseEntity<BookingDto> getBookingById(@PathVariable Long id) {
        return ResponseEntity.ok(bookingService.getBookingById(id));
    }

    //  Get Bookings by User
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BookingDto>> getBookingsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(bookingService.getBookingByUserId(userId));
    }

    //  Get Booking by Booking Number
    @GetMapping("/number/{bookingNumber}")
    public ResponseEntity<BookingDto> getBookingByNumber(@PathVariable String bookingNumber) {
        return ResponseEntity.ok(bookingService.getBookingByNumber(bookingNumber));
    }

    //Cancel Booking
    @PutMapping("/{id}/cancel")
    public ResponseEntity<BookingDto> cancelBooking(@PathVariable Long id) {
        return ResponseEntity.ok(bookingService.cancelBooking(id));
    }

}
