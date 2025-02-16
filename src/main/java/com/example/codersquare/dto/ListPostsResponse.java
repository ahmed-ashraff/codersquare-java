package com.example.codersquare.dto;

import com.example.codersquare.model.Post;

import java.util.List;

public record ListPostsResponse(
        List<Post> posts
) {
}
