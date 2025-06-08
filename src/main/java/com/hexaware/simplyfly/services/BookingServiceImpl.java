package com.hexaware.simplyfly.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.simplyfly.dto.BookingDTO;
import com.hexaware.simplyfly.entities.Booking;
import com.hexaware.simplyfly.entities.Route;
import com.hexaware.simplyfly.entities.User;
import com.hexaware.simplyfly.exceptions.BookingNotFoundException;
import com.hexaware.simplyfly.exceptions.RouteNotFoundException;
import com.hexaware.simplyfly.exceptions.UserNotFoundException;
import com.hexaware.simplyfly.repositories.BookingRepository;
import com.hexaware.simplyfly.repositories.RouteRepository;
import com.hexaware.simplyfly.repositories.UserRepository;

@Service
public class BookingServiceImpl implements IBookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RouteRepository routeRepository;

   
    @Override
    public Booking createBooking(BookingDTO dto) throws UserNotFoundException, RouteNotFoundException {
        User user = userRepository.findById(dto.getUser_id())
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + dto.getUser_id()));

        Route route = routeRepository.findById(dto.getRoute_id())
                .orElseThrow(() -> new RouteNotFoundException("Route not found with ID: " + dto.getRoute_id()));

       

        Booking booking = new Booking();
        booking.setBookedAt(dto.getBookedAt());
        booking.setStatus(dto.getStatus());  // Now uses shared enum directly
        booking.setTotalFare(dto.getTotalFare());
        booking.setUser(user);
        booking.setRoute(route);
      
        return bookingRepository.save(booking);
    }

    @Override
    public Booking updateBooking(Long booking_id, BookingDTO dto) throws BookingNotFoundException, UserNotFoundException, RouteNotFoundException{
        Booking booking = bookingRepository.findById(booking_id)
                .orElseThrow(() -> new BookingNotFoundException("Booking not found with ID: " + booking_id));

        User user = userRepository.findById(dto.getUser_id())
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + dto.getUser_id()));

        Route route = routeRepository.findById(dto.getRoute_id())
                .orElseThrow(() -> new RouteNotFoundException("Route not found with ID: " + dto.getRoute_id()));

       

        booking.setBookedAt(dto.getBookedAt());
        booking.setStatus(dto.getStatus());  // Shared enum
        booking.setTotalFare(dto.getTotalFare());
        booking.setUser(user);
        booking.setRoute(route);
       
        return bookingRepository.save(booking);
    }

    @Override
    public boolean deleteBooking(Long booking_id) throws BookingNotFoundException {
        if (!bookingRepository.existsById(booking_id)) {
            throw new BookingNotFoundException("Booking not found with ID: " + booking_id);
        }
        bookingRepository.deleteById(booking_id);
        return true;
    }

    @Override
    public Booking getBookingById(Long booking_id) throws BookingNotFoundException {
        return bookingRepository.findById(booking_id)
                .orElseThrow(() -> new BookingNotFoundException("Booking not found with ID: " + booking_id));
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
}