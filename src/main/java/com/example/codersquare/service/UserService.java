package com.example.codersquare.service;

import com.example.codersquare.dto.SignUpRequest;

import java.util.UUID;

public interface UserService {
    void createUser(SignUpRequest signUpRequest);
}
