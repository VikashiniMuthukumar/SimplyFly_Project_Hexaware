package com.hexaware.simplyfly.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * FlightDTO carries flight details with validation.
 * @author Vikashini
 * @version 1.0
 */

public class FlightDTO {

    private Long flight_id;

    @NotBlank
    @Size(max = 100, message = "Flight name must be at most 100 characters")
    private String name;

    @NotBlank
    @Size(max = 20, message = "Flight code must be at most 20 characters")
    private String flightCode;

    @NotNull
    @Min(value = 1, message = "Total seats must be at least 1")
    private Integer totalSeats;

    @NotNull
    @Min(value = 0, message = "Cabin baggage limit cannot be negative")
    private Integer cabinBaggageLimit;

    @NotNull
    @Min(value = 0, message = "Check-in baggage limit cannot be negative")
    private Integer checkInBaggageLimit;

    @NotNull
    private Long owner_id;
    @JsonIgnore
    private List<RouteDTO> routes;

    public FlightDTO() {}

    public FlightDTO(Long flight_id, String name, String flightCode,
                     Integer totalSeats, Integer cabinBaggageLimit,
                     Integer checkInBaggageLimit, Long owner_id,
                     List<RouteDTO> routes) {
        this.flight_id = flight_id;
        this.name = name;
        this.flightCode = flightCode;
        this.totalSeats = totalSeats;
        this.cabinBaggageLimit = cabinBaggageLimit;
        this.checkInBaggageLimit = checkInBaggageLimit;
        this.owner_id = owner_id;
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

    public Integer getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(Integer totalSeats) {
        this.totalSeats = totalSeats;
    }

    public Integer getCabinBaggageLimit() {
        return cabinBaggageLimit;
    }

    public void setCabinBaggageLimit(Integer cabinBaggageLimit) {
        this.cabinBaggageLimit = cabinBaggageLimit;
    }

    public Integer getCheckInBaggageLimit() {
        return checkInBaggageLimit;
    }

    public void setCheckInBaggageLimit(Integer checkInBaggageLimit) {
        this.checkInBaggageLimit = checkInBaggageLimit;
    }

    public Long getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(Long owner_id) {
        this.owner_id = owner_id;
    }

    public List<RouteDTO> getRoutes() {
        return routes;
    }

    public void setRoutes(List<RouteDTO> routes) {
        this.routes = routes;
    }

	@Override
	public String toString() {
		return "FlightDTO [flight_id=" + flight_id + ", name=" + name + ", flightCode=" + flightCode + ", totalSeats="
				+ totalSeats + ", cabinBaggageLimit=" + cabinBaggageLimit + ", checkInBaggageLimit="
				+ checkInBaggageLimit + ", owner_id=" + owner_id + ", routes=" + routes + "]";
	}
    
    
}