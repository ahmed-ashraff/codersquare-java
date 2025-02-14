package com.example.codersquare.dto;

import java.util.UUID;

public record SignInResponse(
        UUID id,
        String firstName,
        String lastName,
        String email,
        String username
) {
}
