package com.hexaware.simplyfly.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.simplyfly.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    User findByEmail(String email);

    boolean existsByEmail(String email);

    Optional<Object> findByUsername(String username);

    boolean existsByUsername(String username);
}
