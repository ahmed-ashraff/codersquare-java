package com.example.codersquare.dto;

public record SignUpRequest(
        String firstName,
        String lastName,
        String email,
        String username,
        String password
) {
}
