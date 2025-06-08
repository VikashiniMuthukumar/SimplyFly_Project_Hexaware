package com.hexaware.simplyfly.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.simplyfly.entities.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

}
