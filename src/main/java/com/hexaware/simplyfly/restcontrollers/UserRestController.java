package com.hexaware.simplyfly.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.simplyfly.dto.UserDTO;
import com.hexaware.simplyfly.entities.User;
import com.hexaware.simplyfly.exceptions.UserNotFoundException;
import com.hexaware.simplyfly.services.IUserService;
import com.hexaware.simplyfly.exceptions.UserNotFoundException;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserRestController {

    @Autowired
    private IUserService userService;

    @PostMapping
    public ResponseEntity<String> registerUser(@Valid @RequestBody UserDTO dto) {
        log.info("Registering new user with data: {}", dto);
        User user = userService.registerUser(dto);
        log.info("User registered with ID: {}", user.getUser_id());
        return new ResponseEntity<>("User registered successfully with ID: " + user.getUser_id(), HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<String> updateUser(@PathVariable Long userId, @Valid @RequestBody UserDTO dto) throws UserNotFoundException {
        log.info("Updating user with ID: {}", userId);
        User user = userService.updateUser(userId, dto);
        log.info("User updated: {}", user);
        return ResponseEntity.ok("User updated successfully for ID: " + user.getUser_id());
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) throws UserNotFoundException {
        log.warn("Deleting user with ID: {}", userId);
        userService.deleteUser(userId);
        log.info("User deleted with ID: {}", userId);
        return ResponseEntity.ok("User deleted successfully for ID: " + userId);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) throws UserNotFoundException {
        log.info("Fetching user with ID: {}", userId);
        User user = userService.getUserById(userId);
        log.info("User fetched: {}", user);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        log.info("Fetching all users");
        List<User> users = userService.getAllUsers();
        log.info("Total users fetched: {}", users.size());
        return ResponseEntity.ok(users);
    }
}