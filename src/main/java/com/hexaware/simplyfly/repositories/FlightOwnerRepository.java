package com.hexaware.simplyfly.repositories;

/**
 * Repository interface for the FlightOwner entity.
 * Inherits standard CRUD operations from JpaRepository.
 * 
 * Author: Vikashini
 * Version: 1.0
 */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.simplyfly.entities.FlightOwner;

@Repository
public interface FlightOwnerRepository extends JpaRepository<FlightOwner, Long>{

}
