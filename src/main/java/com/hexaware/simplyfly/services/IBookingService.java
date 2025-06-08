package com.hexaware.simplyfly.services;

import java.util.List;

import com.hexaware.simplyfly.dto.BookingDTO;
import com.hexaware.simplyfly.entities.Booking;
import com.hexaware.simplyfly.exceptions.BookingNotFoundException;
import com.hexaware.simplyfly.exceptions.RouteNotFoundException;
import com.hexaware.simplyfly.exceptions.UserNotFoundException;

public interface IBookingService {
	Booking createBooking(BookingDTO dto) throws UserNotFoundException, RouteNotFoundException;

	Booking updateBooking(Long booking_id, BookingDTO dto) throws BookingNotFoundException, UserNotFoundException,
			RouteNotFoundException;

	boolean deleteBooking(Long booking_id) throws BookingNotFoundException;

	Booking getBookingById(Long booking_id) throws BookingNotFoundException;

	List<Booking> getAllBookings();
}
