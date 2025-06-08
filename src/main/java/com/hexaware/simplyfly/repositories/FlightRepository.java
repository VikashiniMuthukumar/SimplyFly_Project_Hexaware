package com.hexaware.simplyfly.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hexaware.simplyfly.entities.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

	@Modifying
	@Query("UPDATE Flight f SET f.cabinBaggageLimit = :limit WHERE f.flight_id = :flightId")
	int updateCabinBaggageLimit(@Param("limit") int limit, @Param("flightId") Long flightId);
}
