package com.example.codersquare.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

//@Entity
//@Table(name = "posts")
public class Post {
//    @Id
//    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private UUID id;
    private String title;
    private String url;
    private int postedAt;
    private boolean liked;
    private UUID userId;
}
