package com.hexaware.simplyfly.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hexaware.simplyfly.dto.BookingDTO;
import com.hexaware.simplyfly.entities.Booking;
import com.hexaware.simplyfly.exceptions.*;
import com.hexaware.simplyfly.services.IBookingService;
import com.hexaware.simplyfly.entities.Booking;
import com.hexaware.simplyfly.exceptions.BookingNotFoundException;
import com.hexaware.simplyfly.exceptions.RouteNotFoundException;
import com.hexaware.simplyfly.exceptions.UserNotFoundException;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/bookings")
public class BookingRestController {

    @Autowired
    private IBookingService bookingService;

    @PostMapping
    public ResponseEntity<String> createBooking(@Valid @RequestBody BookingDTO dto) 
            throws UserNotFoundException, RouteNotFoundException{
        log.info("Creating booking with data: {}", dto);
        Booking booking = bookingService.createBooking(dto);
        log.info("Booking created with ID: {}", booking.getBooking_id());
        return new ResponseEntity<>("Booking created successfully with ID: " + booking.getBooking_id(), HttpStatus.CREATED);
    }

    @PutMapping("/{bookingId}")
    public ResponseEntity<String> updateBooking(@PathVariable Long bookingId, @Valid @RequestBody BookingDTO dto) 
            throws BookingNotFoundException, UserNotFoundException, RouteNotFoundException {
        log.info("Updating booking with ID: {}", bookingId);
        Booking booking = bookingService.updateBooking(bookingId, dto);
        log.info("Booking updated: {}", booking);
        return ResponseEntity.ok("Booking updated successfully for ID: " + booking.getBooking_id());
    }

    @DeleteMapping("/{bookingId}")
    public ResponseEntity<String> deleteBooking(@PathVariable Long bookingId) throws BookingNotFoundException {
        log.warn("Deleting booking with ID: {}", bookingId);
        bookingService.deleteBooking(bookingId);
        log.info("Booking deleted with ID: {}", bookingId);
        return ResponseEntity.ok("Booking deleted successfully for ID: " + bookingId);
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<Object> getBookingById(@PathVariable Long bookingId) throws BookingNotFoundException {
        log.info("Fetching booking with ID: {}", bookingId);
        Booking booking = bookingService.getBookingById(bookingId);
        log.info("Booking fetched: {}", booking);
        return ResponseEntity.ok(booking);
    }

    @GetMapping
    public ResponseEntity<Object> getAllBookings() {
        log.info("Fetching all bookings");
        List<Booking> bookings = bookingService.getAllBookings();
        log.info("Total bookings fetched: {}", bookings.size());
        return ResponseEntity.ok(bookings);
    }
}