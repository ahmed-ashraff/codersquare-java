package com.example.codersquare.service;

import com.example.codersquare.dto.CreatePostRequest;
import com.example.codersquare.dto.CreatePostResponse;
import com.example.codersquare.dto.ListPostsRequest;
import com.example.codersquare.dto.ListPostsResponse;


public interface PostService {
    ListPostsResponse getAllPosts(ListPostsRequest listPostsRequest);

    CreatePostResponse createPost(CreatePostRequest createPostRequest);
}
