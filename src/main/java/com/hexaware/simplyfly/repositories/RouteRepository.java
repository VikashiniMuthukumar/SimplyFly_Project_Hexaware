package com.hexaware.simplyfly.repositories;

/**
 * Repository interface for the Route entity.
 * Extends JpaRepository to provide CRUD operations on Route.
 *
 * Author: Vikashini
 * Version: 1.0
 */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.simplyfly.entities.Route;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long>{

}
