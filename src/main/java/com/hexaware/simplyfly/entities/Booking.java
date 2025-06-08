package com.hexaware.simplyfly.entities;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.hexaware.simplyfly.enums.BookingStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

/**
 * Entity representing a Booking made by a User on a Route.
 * Author: Vikashini
 * Version: 1.0
 */

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private Long booking_id;

    private LocalDateTime bookedAt = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private BookingStatus status = BookingStatus.CONFIRMED;

    private Double totalFare;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @ManyToOne
    @JoinColumn(name = "route_id")
    @JsonManagedReference
    private Route route;


	public Booking() {
		super();
	}


	public Booking(Long booking_id, LocalDateTime bookedAt, BookingStatus status, Double totalFare, User user,
			Route route) {
		super();
		this.booking_id = booking_id;
		this.bookedAt = bookedAt;
		this.status = status;
		this.totalFare = totalFare;
		this.user = user;
		this.route = route;
	}


	public Long getBooking_id() {
		return booking_id;
	}


	public void setBooking_id(Long booking_id) {
		this.booking_id = booking_id;
	}


	public LocalDateTime getBookedAt() {
		return bookedAt;
	}


	public void setBookedAt(LocalDateTime bookedAt) {
		this.bookedAt = bookedAt;
	}


	public BookingStatus getStatus() {
		return status;
	}


	public void setStatus(BookingStatus status) {
		this.status = status;
	}


	public Double getTotalFare() {
		return totalFare;
	}


	public void setTotalFare(Double totalFare) {
		this.totalFare = totalFare;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Route getRoute() {
		return route;
	}


	public void setRoute(Route route) {
		this.route = route;
	}


	@Override
	public String toString() {
		return "Booking [booking_id=" + booking_id + ", bookedAt=" + bookedAt + ", status=" + status + ", totalFare="
				+ totalFare + ", user=" + user + ", route=" + route + "]";
	}

	
   
}