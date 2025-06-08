package com.hexaware.simplyfly.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.hexaware.simplyfly.enums.UserRole;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 * Entity representing a user with details like name, email, role, and relationships to admin,
 * flight owner, and bookings.
 * 
 * Author: Vikashini
 * Version: 1.0
 */

@Entity
@Table(name = "users")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "user_id")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long user_id;

	private String name;

	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false)
	private String password;

	private String phoneNumber;
	private String username;

	@Enumerated(EnumType.STRING)
	@Column(length = 30)  // <-- Add this to allow longer enum strings
	private UserRole role;


	private LocalDateTime registeredAt = LocalDateTime.now();

	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	@JsonBackReference
	private Admin admin;

	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonBackReference
	private FlightOwner flightOwner;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Booking> bookings = new ArrayList<>();

	public User() {
		super();
	}

	public User(Long user_id, String name, String email, String password, String phoneNumber, String username,
			UserRole role, LocalDateTime registeredAt, Admin admin, FlightOwner flightOwner, List<Booking> bookings) {
		super();
		this.user_id = user_id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.username = username;
		this.role = role;
		this.registeredAt = registeredAt;
		this.admin = admin;
		this.flightOwner = flightOwner;
		this.bookings = bookings;
	}

	public User(Long user_id, String name, String email, String password, String phoneNumber, UserRole role,
			LocalDateTime registeredAt, Admin admin, FlightOwner flightOwner, List<Booking> bookings) {
		this.user_id = user_id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.role = role;
		this.registeredAt = registeredAt;
		this.admin = admin;
		this.flightOwner = flightOwner;
		this.bookings = bookings;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public LocalDateTime getRegisteredAt() {
		return registeredAt;
	}

	public void setRegisteredAt(LocalDateTime registeredAt) {
		this.registeredAt = registeredAt;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public FlightOwner getFlightOwner() {
		return flightOwner;
	}

	public void setFlightOwner(FlightOwner flightOwner) {
		this.flightOwner = flightOwner;
	}

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", name=" + name + ", email=" + email + ", password=" + password
				+ ", phoneNumber=" + phoneNumber + ", role=" + role + ", registeredAt=" + registeredAt + ", admin="
				+ admin + ", flightOwner=" + flightOwner + ", bookings=" + bookings + "]";
	}

}