package com.hexaware.simplyfly.services;

import java.util.List;

import com.hexaware.simplyfly.dto.RouteDTO;
import com.hexaware.simplyfly.entities.Route;
import com.hexaware.simplyfly.exceptions.RouteNotFoundException;

public interface IRouteService {

    Route createRoute(RouteDTO dto);

    Route updateRoute(Long routeId, RouteDTO dto) throws RouteNotFoundException;

    boolean deleteRoute(Long routeId) throws RouteNotFoundException;

    Route getRouteById(Long routeId) throws RouteNotFoundException;

    List<Route> getAllRoutes();
}
