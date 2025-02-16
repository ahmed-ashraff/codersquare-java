package com.example.codersquare.mapper;

import com.example.codersquare.dto.CreatePostRequest;
import com.example.codersquare.dto.CreatePostResponse;
import com.example.codersquare.model.Post;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class CreatePostMapper implements Mapper<Post, CreatePostRequest, CreatePostResponse> {
    @Override
    public Post mapToEntity(CreatePostRequest createPostRequest) {
        Post post = new Post();
        post.setId(UUID.randomUUID());
        post.setTitle(createPostRequest.title());
        post.setUrl(createPostRequest.url());
        post.setPostedAt((int) Instant.now().getEpochSecond());
        post.setLiked(false);

        return post;
    }

    @Override
    public CreatePostResponse mapToResponse(Post post) {
        return null;
    }
}
