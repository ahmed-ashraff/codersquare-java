package com.example.codersquare.service;

import com.example.codersquare.dto.SignInRequest;
import com.example.codersquare.dto.SignInResponse;
import com.example.codersquare.dto.SignUpRequest;
import com.example.codersquare.dto.SignUpResponse;

public interface AuthService {
    SignUpResponse signUpUser(SignUpRequest signUpRequest);

    SignInResponse signInUser(SignInRequest signInRequest);
}
