package com.example.codersquare.mapper;

import com.example.codersquare.dto.SignUpRequest;
import com.example.codersquare.dto.SignUpResponse;
import com.example.codersquare.model.User;

import java.util.UUID;

public class SignUpMapper implements Mapper<User, SignUpRequest, SignUpResponse> {
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
    public SignUpResponse mapToResponse(User user) {
        return new SignUpResponse();
    }
}
