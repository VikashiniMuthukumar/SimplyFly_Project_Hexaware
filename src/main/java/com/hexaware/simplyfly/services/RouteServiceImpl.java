package com.hexaware.simplyfly.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.simplyfly.dto.RouteDTO;
import com.hexaware.simplyfly.entities.Flight;
import com.hexaware.simplyfly.entities.Route;
import com.hexaware.simplyfly.exceptions.RouteNotFoundException;
import com.hexaware.simplyfly.repositories.FlightRepository;
import com.hexaware.simplyfly.repositories.RouteRepository;

@Service
public class RouteServiceImpl implements IRouteService {

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Override
    public Route createRoute(RouteDTO dto) {
        Flight flight = flightRepository.findById(dto.getFlight_id())
                .orElseThrow(() -> new RuntimeException("Flight not found with ID: " + dto.getFlight_id()));

        Route route = new Route();
        route.setOrigin(dto.getOrigin());
        route.setDestination(dto.getDestination());
        route.setDepartureTime(dto.getDepartureTime());
        route.setArrivalTime(dto.getArrivalTime());
        route.setBaseFare(dto.getBaseFare());
        route.setFlight(flight);

        return routeRepository.save(route);
    }

    @Override
    public Route updateRoute(Long routeId, RouteDTO dto) throws RouteNotFoundException {
        Route route = routeRepository.findById(routeId)
                .orElseThrow(() -> new RouteNotFoundException("Route not found with ID: " + routeId));

        Flight flight = flightRepository.findById(dto.getFlight_id())
                .orElseThrow(() -> new RuntimeException("Flight not found with ID: " + dto.getFlight_id()));

        route.setOrigin(dto.getOrigin());
        route.setDestination(dto.getDestination());
        route.setDepartureTime(dto.getDepartureTime());
        route.setArrivalTime(dto.getArrivalTime());
        route.setBaseFare(dto.getBaseFare());
        route.setFlight(flight);

        return routeRepository.save(route);
    }

    @Override
    public boolean deleteRoute(Long routeId) throws RouteNotFoundException {
        if (!routeRepository.existsById(routeId)) {
            throw new RouteNotFoundException("Route not found with ID: " + routeId);
        }
        routeRepository.deleteById(routeId);
        return true;
    }

    @Override
    public Route getRouteById(Long routeId) throws RouteNotFoundException {
        return routeRepository.findById(routeId)
                .orElseThrow(() -> new RouteNotFoundException("Route not found with ID: " + routeId));
    }

    @Override
    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }
}