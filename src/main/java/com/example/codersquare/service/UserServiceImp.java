package com.example.codersquare.service;

import com.example.codersquare.dto.SignInRequest;
import com.example.codersquare.dto.SignInResponse;
import com.example.codersquare.dto.SignUpRequest;
import com.example.codersquare.exception.InvalidCredentialsException;
import com.example.codersquare.exception.UserNotFoundException;
import com.example.codersquare.mapper.SignInMapper;
import com.example.codersquare.mapper.SignUpMapper;
import com.example.codersquare.model.User;
import com.example.codersquare.repository.UserRepository;
import com.example.codersquare.exception.UserAlreadyExistsException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;
    private final SignInMapper signInMapper;
    private final SignUpMapper signUpMapper;

    public UserServiceImp(UserRepository userRepository, SignInMapper signInMapper, SignUpMapper signUpMapper) {
        this.userRepository = userRepository;
        this.signInMapper = signInMapper;
        this.signUpMapper = signUpMapper;
    }

    @Override
    public void signUpUser(SignUpRequest signUpRequest) {
        if (signUpRequest.email() == null || signUpRequest.username() == null || signUpRequest.password() == null ||
                signUpRequest.firstName() == null || signUpRequest.lastName() == null) {
            throw new IllegalArgumentException("All fields are required");
        }

        boolean existing = userRepository.existsUserByUserName(signUpRequest.username()) || userRepository.existsUserByEmail(signUpRequest.email());

        if (existing) {
            throw new UserAlreadyExistsException("User already exists");
        }
        userRepository.save(signUpMapper.mapToEntity(signUpRequest));
    }

    @Override
    public SignInResponse signInUser(SignInRequest signInRequest) {
        if (signInRequest.login() == null || signInRequest.password() == null) {
            throw new IllegalArgumentException("Username/Email and password are required");
        }

        User user = userRepository.findByUserNameOrEmail(signInRequest.login(), signInRequest.login()).orElseThrow(() -> new UserNotFoundException("User not found"));

        if (!Objects.equals(user.getPassword(), signInRequest.password())) {
            throw new InvalidCredentialsException("Invalid credentials");
        }
        return signInMapper.mapToResponse(user);
    }
}
