package com.hexaware.simplyfly.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.simplyfly.dto.UserDTO;
import com.hexaware.simplyfly.entities.User;
import com.hexaware.simplyfly.exceptions.UserNotFoundException;
import com.hexaware.simplyfly.repositories.UserRepository;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User registerUser(UserDTO dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());
        user.setPhoneNumber(dto.getPhoneNumber());
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long userId, UserDTO dto) throws UserNotFoundException {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());
        user.setPhoneNumber(dto.getPhoneNumber());
        return userRepository.save(user);
    }

    @Override
    public boolean deleteUser(Long userId) throws UserNotFoundException {
    	User user = userRepository.findById(userId)
    		    .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));

    		if (user.getFlightOwner() != null) {
    		    user.getFlightOwner().setUser(null);   
    		    user.setFlightOwner(null);             
    		}

    		userRepository.delete(user);
    		return true;
    }


    @Override
    public User getUserById(Long userId) throws UserNotFoundException {
        return userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}