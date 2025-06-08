package com.hexaware.simplyfly.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.simplyfly.entities.Route;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long>{

}
