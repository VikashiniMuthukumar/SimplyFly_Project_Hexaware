package com.hexaware.simplyfly.repositories;

/**
 * Repository interface for Booking entity.
 * Provides built-in CRUD and query support through JpaRepository.
 * 
 * Author: Vikashini
 * Version: 1.0
 */
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.simplyfly.entities.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

}
