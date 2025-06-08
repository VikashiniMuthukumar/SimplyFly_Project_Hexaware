package com.hexaware.simplyfly.restcontrollers;

/**
 * REST controller for managing Flight entities.
 * Supports CRUD operations for flights.
 * 
 * Author: Vikashini
 * Version: 1.0
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hexaware.simplyfly.dto.FlightDTO;
import com.hexaware.simplyfly.entities.Flight;
import com.hexaware.simplyfly.exceptions.FlightNotFoundException;
import com.hexaware.simplyfly.exceptions.FlightOwnerNotFoundException;
import com.hexaware.simplyfly.services.IFlightService;
import com.hexaware.simplyfly.entities.Flight;
import com.hexaware.simplyfly.exceptions.FlightNotFoundException;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/flights")
public class FlightRestController {

    @Autowired
    private IFlightService flightService;

    @PostMapping
    public ResponseEntity<String> createFlight(@Valid @RequestBody FlightDTO dto) throws FlightOwnerNotFoundException {
        log.info("Creating Flight with data: {}", dto);
        Flight flight = flightService.createFlight(dto);
        log.info("Flight created with ID: {}", flight.getFlight_id());
        return new ResponseEntity<>("Flight created successfully with ID: " + flight.getFlight_id(), HttpStatus.CREATED);
    }

    @PutMapping("/{flightId}")
    public ResponseEntity<String> updateFlight(@PathVariable Long flightId, @Valid @RequestBody FlightDTO dto) throws FlightNotFoundException, FlightOwnerNotFoundException {
        log.info("Updating Flight with ID: {}", flightId);
        Flight flight = flightService.updateFlight(flightId, dto);
        log.info("Flight updated: {}", flight);
        return ResponseEntity.ok("Flight updated successfully for ID: " + flight.getFlight_id());
    }

    @DeleteMapping("/{flightId}")
    public ResponseEntity<String> deleteFlight(@PathVariable Long flightId) throws FlightNotFoundException {
        log.warn("Deleting Flight with ID: {}", flightId);
        flightService.deleteFlight(flightId);
        log.info("Flight deleted with ID: {}", flightId);
        return ResponseEntity.ok("Flight deleted successfully for ID: " + flightId);
    }

    @GetMapping("/{flightId}")
    public ResponseEntity<Flight> getFlightById(@PathVariable Long flightId) throws FlightNotFoundException {
        log.info("Fetching Flight with ID: {}", flightId);
        Flight flight = flightService.getFlightById(flightId);
        log.info("Flight fetched: {}", flight);
        return ResponseEntity.ok(flight);
    }

    @GetMapping
    public ResponseEntity<List<Flight>> getAllFlights() {
        log.info("Fetching all Flights");
        List<Flight> flights = flightService.getAllFlights();
        log.info("Total Flights fetched: {}", flights.size());
        return ResponseEntity.ok(flights);
    }
}