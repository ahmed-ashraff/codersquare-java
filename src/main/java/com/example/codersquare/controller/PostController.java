package com.example.codersquare.controller;

import com.example.codersquare.dto.CreatePostRequest;
import com.example.codersquare.dto.CreatePostResponse;
import com.example.codersquare.dto.ListPostsRequest;
import com.example.codersquare.dto.ListPostsResponse;
import com.example.codersquare.service.PostServiceImp;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1")
public class PostController {
    private final PostServiceImp postServiceImp;

    public PostController(PostServiceImp postServiceImp) {
        this.postServiceImp = postServiceImp;
    }

    @GetMapping(path = "/posts")
    public ListPostsResponse listPosts(@RequestBody ListPostsRequest request) {
        return postServiceImp.getAllPosts(request);
    }

    @PostMapping(path = "/posts")
    public CreatePostResponse createPost(@RequestBody CreatePostRequest createPostRequest) {
        return postServiceImp.createPost(createPostRequest);
    }
}
