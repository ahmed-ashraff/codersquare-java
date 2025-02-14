package com.example.codersquare.controller;

import com.example.codersquare.dto.SignUpRequest;
import com.example.codersquare.exception.UserAlreadyExistsException;
import com.example.codersquare.service.UserServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserServiceImp userServiceImp;

    public UserController(UserServiceImp userServiceImp) {
        this.userServiceImp = userServiceImp;
    }

    @PostMapping(path = "/signUp")
    public ResponseEntity<String> signUp(@RequestBody SignUpRequest signUpRequest) {
        try {
            userServiceImp.createUser(signUpRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }
}
