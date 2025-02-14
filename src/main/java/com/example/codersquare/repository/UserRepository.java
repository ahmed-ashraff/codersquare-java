package com.example.codersquare.repository;

import com.example.codersquare.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUserNameOrEmail(String username, String email);
    boolean existsUserByEmail(String email);
    boolean existsUserByUserName(String username);
}
