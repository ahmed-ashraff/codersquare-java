package com.example.codersquare.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "posts")
public class Post {
    @Id
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private UUID id;
    private String title;

    @Column(unique = true)
    private String url;

    private int postedAt;
    private boolean liked;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
