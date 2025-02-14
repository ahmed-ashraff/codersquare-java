package com.example.codersquare.controller;

import com.example.codersquare.dto.SignInRequest;
import com.example.codersquare.dto.SignInResponse;
import com.example.codersquare.dto.SignUpRequest;
import com.example.codersquare.exception.InvalidCredentialsException;
import com.example.codersquare.exception.UserAlreadyExistsException;
import com.example.codersquare.exception.UserNotFoundException;
import com.example.codersquare.service.AuthServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class AuthController {
    private final AuthServiceImp userServiceImp;

    public AuthController(AuthServiceImp userServiceImp) {
        this.userServiceImp = userServiceImp;
    }

    @PostMapping(path = "/signUp")
    public ResponseEntity<String> signUp(@RequestBody SignUpRequest signUpRequest) {
        userServiceImp.signUpUser(signUpRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
    }

    @PostMapping(path = "/signIn")
    public ResponseEntity<?> signIn(@RequestBody SignInRequest signInRequest) {
        SignInResponse signInResponse = userServiceImp.signInUser(signInRequest);
        return ResponseEntity.ok(signInResponse);
    }
}
