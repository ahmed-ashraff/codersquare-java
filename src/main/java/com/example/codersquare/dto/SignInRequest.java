package com.example.codersquare.dto;

public record SignInRequest(
        String login, // Can be username or email
        String password
) {
}
