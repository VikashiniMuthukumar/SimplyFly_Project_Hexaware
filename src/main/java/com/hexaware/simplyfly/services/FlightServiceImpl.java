package com.hexaware.simplyfly.services;

/**
 * Service implementation for CRUD operations on Flight entities.
 * Author: Vikashini
 * Version: 1.0
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.simplyfly.dto.FlightDTO;
import com.hexaware.simplyfly.entities.Flight;
import com.hexaware.simplyfly.entities.FlightOwner;
import com.hexaware.simplyfly.exceptions.FlightNotFoundException;
import com.hexaware.simplyfly.exceptions.FlightOwnerNotFoundException;
import com.hexaware.simplyfly.repositories.FlightOwnerRepository;
import com.hexaware.simplyfly.repositories.FlightRepository;

@Service
public class FlightServiceImpl implements IFlightService {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private FlightOwnerRepository flightOwnerRepository;

    @Override
    public Flight createFlight(FlightDTO dto) throws FlightOwnerNotFoundException {
        Flight flight = new Flight();
        flight.setName(dto.getName());
        flight.setFlightCode(dto.getFlightCode());
        flight.setTotalSeats(dto.getTotalSeats());
        flight.setCabinBaggageLimit(dto.getCabinBaggageLimit());
        flight.setCheckInBaggageLimit(dto.getCheckInBaggageLimit());

        FlightOwner owner = flightOwnerRepository.findById(dto.getOwner_id())
            .orElseThrow(() -> new FlightOwnerNotFoundException("Flight owner not found with ID: " + dto.getOwner_id()));

        flight.setOwner(owner);

        return flightRepository.save(flight);
    }

    @Override
    public Flight updateFlight(Long flight_id, FlightDTO dto) throws FlightNotFoundException, FlightOwnerNotFoundException {
        Flight flight = flightRepository.findById(flight_id)
                .orElseThrow(() -> new FlightNotFoundException("Flight not found with ID: " + flight_id));

        flight.setName(dto.getName());
        flight.setFlightCode(dto.getFlightCode());
        flight.setTotalSeats(dto.getTotalSeats());
        flight.setCabinBaggageLimit(dto.getCabinBaggageLimit());
        flight.setCheckInBaggageLimit(dto.getCheckInBaggageLimit());

        FlightOwner owner = flightOwnerRepository.findById(dto.getOwner_id())
            .orElseThrow(() -> new FlightOwnerNotFoundException("Flight owner not found with ID: " + dto.getOwner_id()));

        flight.setOwner(owner);

        return flightRepository.save(flight);
    }

    @Override
    public boolean deleteFlight(Long flight_id) throws FlightNotFoundException {
        if (!flightRepository.existsById(flight_id)) {
            throw new FlightNotFoundException("Flight not found with ID: " + flight_id);
        }
        flightRepository.deleteById(flight_id);
        return true;
    }

    @Override
    public Flight getFlightById(Long flight_id) throws FlightNotFoundException {
        return flightRepository.findById(flight_id)
                .orElseThrow(() -> new FlightNotFoundException("Flight not found with ID: " + flight_id));
    }

    @Override
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }
}