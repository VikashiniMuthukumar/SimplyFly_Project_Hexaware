package com.hexaware.simplyfly.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.simplyfly.entities.FlightOwner;

@Repository
public interface FlightOwnerRepository extends JpaRepository<FlightOwner, Long>{

}
