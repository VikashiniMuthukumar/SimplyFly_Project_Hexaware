package com.hexaware.simplyfly.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public class RouteDTO {

    private Long route_id;

    @NotNull
    @Size(max = 50, message = "Origin must be at most 50 characters")
    private String origin;

    @NotNull
    @Size(max = 50, message = "Destination must be at most 50 characters")
    private String destination;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    private LocalDateTime departureTime;

    
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    private LocalDateTime arrivalTime;


    @NotNull
    @PositiveOrZero(message = "Base fare must be zero or positive")
    private Double baseFare;

    
    private Long flight_id;

    @JsonIgnore
    private List<BookingDTO> bookings;

    public RouteDTO() {}

    public RouteDTO(Long route_id, String origin, String destination, LocalDateTime departureTime,
                    LocalDateTime arrivalTime, Double baseFare, Long flight_id,
                    List<BookingDTO> bookings) {
        this.route_id = route_id;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.baseFare = baseFare;
        this.flight_id = flight_id;
        this.bookings = bookings;
    }

    public Long getRoute_id() {
        return route_id;
    }

    public void setRoute_id(Long route_id) {
        this.route_id = route_id;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Double getBaseFare() {
        return baseFare;
    }

    public void setBaseFare(Double baseFare) {
        this.baseFare = baseFare;
    }

    public Long getFlight_id() {
        return flight_id;
    }

    public void setFlight_id(Long flight_id) {
        this.flight_id = flight_id;
    }

    public List<BookingDTO> getBookings() {
        return bookings;
    }

    public void setBookings(List<BookingDTO> bookings) {
        this.bookings = bookings;
    }

	@Override
	public String toString() {
		return "RouteDTO [route_id=" + route_id + ", origin=" + origin + ", destination=" + destination
				+ ", departureTime=" + departureTime + ", arrivalTime=" + arrivalTime + ", baseFare=" + baseFare
				+ ", flight_id=" + flight_id + ", bookings=" + bookings + "]";
	}
    
    
}