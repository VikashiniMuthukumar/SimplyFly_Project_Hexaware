package com.hexaware.simplyfly.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class,
		  property = "flight_id")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flight_id")
    private Long flight_id;

    private String name;

    @Column(unique = true)
    private String flightCode;

    private int totalSeats;

    private int cabinBaggageLimit;

    private int checkInBaggageLimit;

    
    @ManyToOne
    @JoinColumn(name = "owner_id")
    @JsonIgnore
    private FlightOwner owner;

    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Route> routes = new ArrayList<>();
    
	public Flight() {
		super();
	}

	public Flight(Long flight_id, String name, String flightCode, int totalSeats, int cabinBaggageLimit,
			int checkInBaggageLimit, FlightOwner owner, List<Route> routes) {
		super();
		this.flight_id = flight_id;
		this.name = name;
		this.flightCode = flightCode;
		this.totalSeats = totalSeats;
		this.cabinBaggageLimit = cabinBaggageLimit;
		this.checkInBaggageLimit = checkInBaggageLimit;
		this.owner = owner;
		this.routes = routes;
	}

	public Long getFlight_id() {
		return flight_id;
	}

	public void setFlight_id(Long flight_id) {
		this.flight_id = flight_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFlightCode() {
		return flightCode;
	}

	public void setFlightCode(String flightCode) {
		this.flightCode = flightCode;
	}

	public int getTotalSeats() {
		return totalSeats;
	}

	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}

	public int getCabinBaggageLimit() {
		return cabinBaggageLimit;
	}

	public void setCabinBaggageLimit(int cabinBaggageLimit) {
		this.cabinBaggageLimit = cabinBaggageLimit;
	}

	public int getCheckInBaggageLimit() {
		return checkInBaggageLimit;
	}

	public void setCheckInBaggageLimit(int checkInBaggageLimit) {
		this.checkInBaggageLimit = checkInBaggageLimit;
	}

	public FlightOwner getOwner() {
		return owner;
	}

	public void setOwner(FlightOwner owner) {
		this.owner = owner;
	}

	public List<Route> getRoutes() {
		return routes;
	}

	public void setRoutes(List<Route> routes) {
		this.routes = routes;
	}

	@Override
	public String toString() {
		return "Flight [flight_id=" + flight_id + ", name=" + name + ", flightCode=" + flightCode + ", totalSeats="
				+ totalSeats + ", cabinBaggageLimit=" + cabinBaggageLimit + ", checkInBaggageLimit="
				+ checkInBaggageLimit + ", owner=" + owner + ", routes=" + routes + "]";
	}





	
	
}