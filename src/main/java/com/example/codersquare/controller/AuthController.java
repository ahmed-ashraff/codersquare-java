package com.example.codersquare.controller;

import com.example.codersquare.dto.SignInRequest;
import com.example.codersquare.dto.SignInResponse;
import com.example.codersquare.dto.SignUpRequest;
import com.example.codersquare.dto.SignUpResponse;
import com.example.codersquare.service.AuthServiceImp;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class AuthController {
    private final AuthServiceImp userServiceImp;

    public AuthController(AuthServiceImp userServiceImp) {
        this.userServiceImp = userServiceImp;
    }

    @PostMapping(path = "/signUp")
    public SignUpResponse signUp(@RequestBody SignUpRequest signUpRequest) {
        return userServiceImp.signUpUser(signUpRequest);
    }

    @PostMapping(path = "/signIn")
    public SignInResponse signIn(@RequestBody SignInRequest signInRequest) {
        return userServiceImp.signInUser(signInRequest);
    }
}
