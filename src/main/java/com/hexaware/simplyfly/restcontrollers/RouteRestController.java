package com.hexaware.simplyfly.restcontrollers;

/**
 * REST controller for managing Route entities.
 * Provides endpoints to create, update, delete, and fetch routes.
 * Uses IRouteService for business logic.
 * 
 * Author: Vikashini
 * Version: 1.0
 */


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hexaware.simplyfly.dto.RouteDTO;
import com.hexaware.simplyfly.entities.Route;
import com.hexaware.simplyfly.exceptions.RouteNotFoundException;
import com.hexaware.simplyfly.services.IRouteService;
import com.hexaware.simplyfly.entities.Route;
import com.hexaware.simplyfly.exceptions.RouteNotFoundException;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/routes")
public class RouteRestController {

    @Autowired
    private IRouteService routeService;

    @PostMapping
    public ResponseEntity<String> createRoute(@Valid @RequestBody RouteDTO dto) {
        log.info("Creating Route with data: {}", dto);
        Route route = routeService.createRoute(dto);
        log.info("Route created with ID: {}", route.getRoute_id());
        return new ResponseEntity<>("Route created successfully with ID: " + route.getRoute_id(), HttpStatus.CREATED);
    }

    @PutMapping("/{routeId}")
    public ResponseEntity<String> updateRoute(@PathVariable Long routeId, @Valid @RequestBody RouteDTO dto) throws RouteNotFoundException {
        log.info("Updating Route with ID: {}", routeId);
        Route route = routeService.updateRoute(routeId, dto);
        log.info("Route updated: {}", route);
        return ResponseEntity.ok("Route updated successfully for ID: " + route.getRoute_id());
    }

    @DeleteMapping("/{routeId}")
    public ResponseEntity<String> deleteRoute(@PathVariable Long routeId) throws RouteNotFoundException {
        log.warn("Deleting Route with ID: {}", routeId);
        routeService.deleteRoute(routeId);
        log.info("Route deleted with ID: {}", routeId);
        return ResponseEntity.ok("Route deleted successfully for ID: " + routeId);
    }

    @GetMapping("/{routeId}")
    public ResponseEntity<Route> getRouteById(@PathVariable Long routeId) throws RouteNotFoundException {
        log.info("Fetching Route with ID: {}", routeId);
        Route route = routeService.getRouteById(routeId);
        log.info("Route fetched: {}", route);
        return ResponseEntity.ok(route);
    }

    @GetMapping
    public ResponseEntity<List<Route>> getAllRoutes() {
        log.info("Fetching all Routes");
        List<Route> routes = routeService.getAllRoutes();
        log.info("Total Routes fetched: {}", routes.size());
        return ResponseEntity.ok(routes);
    }
}