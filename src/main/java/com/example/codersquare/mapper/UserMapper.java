package com.example.codersquare.mapper;

import com.example.codersquare.dto.SignUpRequest;
import com.example.codersquare.model.User;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserMapper implements Mapper<User, SignUpRequest> {

    @Override
    public User mapToEntity(SignUpRequest signUpRequest) {
        User user = new User();
        user.setId(UUID.randomUUID());
        user.setFirstName(signUpRequest.firstName());
        user.setLastName(signUpRequest.lastName());
        user.setEmail(signUpRequest.email());
        user.setUserName(signUpRequest.username());
        user.setPassword(signUpRequest.password());
        return user;
    }

    @Override
    public SignUpRequest mapToDTO(User user) {
        return new SignUpRequest(
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getUserName(),
                user.getPassword()
        );
    }
}
