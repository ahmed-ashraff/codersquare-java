package com.example.codersquare.service;

import com.example.codersquare.dto.SignInRequest;
import com.example.codersquare.dto.SignInResponse;
import com.example.codersquare.dto.SignUpRequest;

public interface UserService {
    void signUpUser(SignUpRequest signUpRequest);

    SignInResponse signInUser(SignInRequest signInRequest);
}
