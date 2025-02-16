package com.example.codersquare.service;

import com.example.codersquare.dto.CreatePostRequest;
import com.example.codersquare.dto.CreatePostResponse;
import com.example.codersquare.dto.ListPostsRequest;
import com.example.codersquare.dto.ListPostsResponse;
import com.example.codersquare.exception.UrlPostAlreadyExistsException;
import com.example.codersquare.exception.UserNotFoundException;
import com.example.codersquare.mapper.CreatePostMapper;
import com.example.codersquare.model.Post;
import com.example.codersquare.model.User;
import com.example.codersquare.repository.PostRepository;
import com.example.codersquare.repository.UserRepository;
import org.springframework.stereotype.Service;


@Service
public class PostServiceImp implements PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CreatePostMapper createPostMapper;

    public PostServiceImp(PostRepository postRepository, UserRepository userRepository, CreatePostMapper createPostMapper) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.createPostMapper = createPostMapper;
    }

    @Override
    public ListPostsResponse getAllPosts(ListPostsRequest listPostsRequest) {
        return new ListPostsResponse(postRepository.findAll());
    }

    @Override
    public CreatePostResponse createPost(CreatePostRequest createPostRequest) {
        validateCreatePostRequest(createPostRequest);
        User user = checkIfUrlExists(createPostRequest);

        Post post = createPostMapper.mapToEntity(createPostRequest);
        post.setUser(user);

        postRepository.save(post);
        return new CreatePostResponse();
    }

    private User checkIfUrlExists(CreatePostRequest createPostRequest) {
        User user = userRepository.findById(createPostRequest.userId()).orElseThrow(() -> new UserNotFoundException("User not found with ID: " + createPostRequest.userId()));

        if (postRepository.existsPostByUrl(createPostRequest.url())) {
            throw new UrlPostAlreadyExistsException("Url already exists");
        }
        return user;
    }

    private static void validateCreatePostRequest(CreatePostRequest createPostRequest) {
        if (createPostRequest.url() == null || createPostRequest.title() == null || createPostRequest.userId() == null) {
            throw new IllegalArgumentException("title, url and userId are required");
        }
    }
}
