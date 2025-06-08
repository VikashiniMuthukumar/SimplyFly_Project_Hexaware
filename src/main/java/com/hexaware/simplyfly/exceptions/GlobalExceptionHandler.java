package com.hexaware.simplyfly.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global exception handler to manage and respond to application-wide exceptions.
 * 
 * It captures various custom and generic exceptions, returning meaningful HTTP
 * responses to the client.
 * 
 * Author: Vikashini  
 * Version: 1.0
 */

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AdminNotFoundException.class)
    public ResponseEntity<String> handleAdminNotFound(AdminNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BookingNotFoundException.class)
    public ResponseEntity<String> handleBookingNotFound(BookingNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(FlightNotFoundException.class)
    public ResponseEntity<String> handleFlightNotFound(FlightNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FlightOwnerNotFoundException.class)
    public ResponseEntity<String> handleFlightOwnerNotFound(FlightOwnerNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }



    @ExceptionHandler(RouteNotFoundException.class)
    public ResponseEntity<String> handleRouteNotFound(RouteNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFound(UserNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return new ResponseEntity<>("Internal Server Error: " + ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

