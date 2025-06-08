package com.hexaware.simplyfly.dto;

import com.hexaware.simplyfly.enums.UserRole;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

public class UserDTO {

    private Long user_id;

    @NotBlank
    @Size(max = 100)
    private String name;

    @NotBlank
    @Email
    @Size(max = 100)
    private String email;

    @NotBlank
    @Size(min = 6, max = 100, message = "Password must be between 6 and 100 characters")
    private String password;

    @Size(max = 15)
    @Pattern(regexp = "^\\+?[0-9]*$", message = "Phone number must be numeric")
    private String phoneNumber;

    @NotNull
    private UserRole role;

    private LocalDateTime registeredAt;

    private Long admin_id;
    private Long flightOwner_id;

    public UserDTO() {
    	super();
    }

    public UserDTO(Long user_id, String name, String email, String password, String phoneNumber,
                   UserRole role, LocalDateTime registeredAt,
                   Long admin_id, Long flightOwner_id) {
        this.user_id = user_id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.registeredAt = registeredAt;
        this.admin_id = admin_id;
        this.flightOwner_id = flightOwner_id;
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

    public Long getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(Long admin_id) {
        this.admin_id = admin_id;
    }

    public Long getFlightOwner_id() {
        return flightOwner_id;
    }

    public void setFlightOwner_id(Long flightOwner_id) {
        this.flightOwner_id = flightOwner_id;
    }

	@Override
	public String toString() {
		return "UserDTO [user_id=" + user_id + ", name=" + name + ", email=" + email + ", password=" + password
				+ ", phoneNumber=" + phoneNumber + ", role=" + role + ", registeredAt=" + registeredAt + ", admin_id="
				+ admin_id + ", flightOwner_id=" + flightOwner_id + "]";
	}
    
    
}