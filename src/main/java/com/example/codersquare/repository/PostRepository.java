package com.example.codersquare.repository;

import com.example.codersquare.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {
    boolean existsPostByUrl(String url);
}
