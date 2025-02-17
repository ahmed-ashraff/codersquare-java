package com.example.codersquare.mapper;

import com.example.codersquare.dto.SignUpRequest;
import com.example.codersquare.dto.SignUpResponse;
import com.example.codersquare.model.User;
import com.example.codersquare.security.JwtTokenProvider;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SignUpMapper implements Mapper<User, SignUpRequest, SignUpResponse> {
    private final JwtTokenProvider jwtTokenProvider;

    public SignUpMapper(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

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
        String jwt = jwtTokenProvider.generateJWT(user.getUsername());
        return new SignUpResponse(jwt);
    }
}
