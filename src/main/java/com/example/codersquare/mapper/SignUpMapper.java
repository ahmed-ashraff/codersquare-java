package com.example.codersquare.mapper;

import com.example.codersquare.dto.SignUpRequest;
import com.example.codersquare.dto.SignUpResponse;
import com.example.codersquare.model.User;
import com.example.codersquare.security.JwtTokenProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SignUpMapper implements Mapper<User, SignUpRequest, SignUpResponse> {
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    public SignUpMapper(JwtTokenProvider jwtTokenProvider, PasswordEncoder passwordEncoder) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User mapToEntity(SignUpRequest signUpRequest) {
        User user = new User();
        user.setId(UUID.randomUUID());
        user.setFirstName(signUpRequest.firstName());
        user.setLastName(signUpRequest.lastName());
        user.setEmail(signUpRequest.email());
        user.setUserName(signUpRequest.username());
        user.setPassword(passwordEncoder.encode(signUpRequest.password()));
        return user;
    }

    @Override
    public SignUpResponse mapToResponse(User user) {
        String jwt = jwtTokenProvider.generateJWT(user.getUsername(), user.getId());
        return new SignUpResponse(jwt);
    }
}
