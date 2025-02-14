package com.example.codersquare.service;

import com.example.codersquare.dto.SignUpRequest;
import com.example.codersquare.mapper.UserMapper;
import com.example.codersquare.repository.UserRepository;
import com.example.codersquare.exception.UserAlreadyExistsException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImp(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public void createUser(SignUpRequest signUpRequest) {
        if (signUpRequest.email() == null || signUpRequest.username() == null || signUpRequest.password() == null ||
                signUpRequest.firstName() == null || signUpRequest.lastName() == null) {
            throw new IllegalArgumentException("All fields are required");
        }

        boolean existing = userRepository.existsUserByUserName(signUpRequest.username()) || userRepository.existsUserByEmail(signUpRequest.email());

        if (existing) {
            throw new UserAlreadyExistsException("User already exists");
        }
        userRepository.save(userMapper.mapToEntity(signUpRequest));
    }
}
