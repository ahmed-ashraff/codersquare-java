package com.example.codersquare.mapper;

import com.example.codersquare.dto.SignInRequest;
import com.example.codersquare.dto.SignInResponse;
import com.example.codersquare.model.User;
import com.example.codersquare.security.JwtTokenProvider;
import org.springframework.stereotype.Service;


@Service
public class SignInMapper implements Mapper<User, SignInRequest, SignInResponse> {
    private final JwtTokenProvider jwtTokenProvider;

    public SignInMapper(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public User mapToEntity(SignInRequest signInRequest) {
        throw new UnsupportedOperationException("Mapping from SignInRequest to User is not supported.");
    }

    @Override
    public SignInResponse mapToResponse(User user) {
        return new SignInResponse(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getUsername(),
                jwtTokenProvider.generateJWT(user.getUsername(), user.getId())
        );
    }
}
