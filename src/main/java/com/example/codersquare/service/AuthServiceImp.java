package com.example.codersquare.service;

import com.example.codersquare.dto.SignInRequest;
import com.example.codersquare.dto.SignInResponse;
import com.example.codersquare.dto.SignUpRequest;
import com.example.codersquare.dto.SignUpResponse;
import com.example.codersquare.exception.InvalidCredentialsException;
import com.example.codersquare.exception.UserNotFoundException;
import com.example.codersquare.mapper.SignInMapper;
import com.example.codersquare.mapper.SignUpMapper;
import com.example.codersquare.model.User;
import com.example.codersquare.repository.UserRepository;
import com.example.codersquare.exception.UserAlreadyExistsException;
import com.example.codersquare.security.JwtTokenProvider;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthServiceImp implements AuthService {
    private final UserRepository userRepository;
    private final SignInMapper signInMapper;
    private final SignUpMapper signUpMapper;
    private final JwtTokenProvider jwtTokenProvider;


    public AuthServiceImp(UserRepository userRepository, SignInMapper signInMapper, SignUpMapper signUpMapper, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.signInMapper = signInMapper;
        this.signUpMapper = signUpMapper;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public SignUpResponse signUpUser(SignUpRequest signUpRequest) {
        validateSignUpRequest(signUpRequest);
        checkIfUserExists(signUpRequest);
        userRepository.save(signUpMapper.mapToEntity(signUpRequest));

        String jwt = jwtTokenProvider.generateJWT(signUpRequest.username());
        return new SignUpResponse(jwt);
    }

    @Override
    public SignInResponse signInUser(SignInRequest signInRequest) {
        validateSignInRequest(signInRequest);
        User user = validateUserCredentials(signInRequest);
        return signInMapper.mapToResponse(user);
    }

    private void checkIfUserExists(SignUpRequest signUpRequest) {
        boolean existing = userRepository.existsUserByUserName(signUpRequest.username()) || userRepository.existsUserByEmail(signUpRequest.email());

        if (existing) {
            throw new UserAlreadyExistsException("User already exists");
        }
    }

    private static void validateSignUpRequest(SignUpRequest signUpRequest) {
        if (signUpRequest.email() == null || signUpRequest.username() == null || signUpRequest.password() == null ||
                signUpRequest.firstName() == null || signUpRequest.lastName() == null) {
            throw new IllegalArgumentException("All fields are required");
        }
    }

    private User validateUserCredentials(SignInRequest signInRequest) {
        User user = userRepository.findByUserNameOrEmail(signInRequest.login(), signInRequest.login()).orElseThrow(() -> new UserNotFoundException("User not found"));

        if (!Objects.equals(user.getPassword(), signInRequest.password())) {
            throw new InvalidCredentialsException("Invalid credentials");
        }
        return user;
    }

    private static void validateSignInRequest(SignInRequest signInRequest) {
        if (signInRequest.login() == null || signInRequest.password() == null) {
            throw new IllegalArgumentException("Username/Email and password are required");
        }
    }
}
