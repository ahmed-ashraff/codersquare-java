package com.example.codersquare.dto;

import java.util.UUID;

public record CreatePostRequest(
        String title,
        String url,
        UUID userId
) {
}
