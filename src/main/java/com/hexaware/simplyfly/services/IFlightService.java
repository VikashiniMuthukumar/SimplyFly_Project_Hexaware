package com.hexaware.simplyfly.services;

import java.util.List;

import com.hexaware.simplyfly.dto.FlightDTO;
import com.hexaware.simplyfly.entities.Flight;
import com.hexaware.simplyfly.exceptions.FlightNotFoundException;
import com.hexaware.simplyfly.exceptions.FlightOwnerNotFoundException;

public interface IFlightService {
    Flight createFlight(FlightDTO dto) throws FlightOwnerNotFoundException;
    Flight updateFlight(Long flight_id, FlightDTO dto) throws FlightNotFoundException, FlightOwnerNotFoundException;
    boolean deleteFlight(Long flight_id) throws FlightNotFoundException;
    Flight getFlightById(Long flight_id) throws FlightNotFoundException;
    List<Flight> getAllFlights();
}
