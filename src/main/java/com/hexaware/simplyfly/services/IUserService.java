package com.hexaware.simplyfly.services;

import java.util.List;

import com.hexaware.simplyfly.dto.UserDTO;
import com.hexaware.simplyfly.entities.User;
import com.hexaware.simplyfly.exceptions.UserNotFoundException;

public interface IUserService {


    User registerUser(UserDTO dto);

    User updateUser(Long userId, UserDTO dto) throws UserNotFoundException;

    boolean deleteUser(Long userId) throws UserNotFoundException;

    User getUserById(Long userId) throws UserNotFoundException;

    List<User> getAllUsers();
}
