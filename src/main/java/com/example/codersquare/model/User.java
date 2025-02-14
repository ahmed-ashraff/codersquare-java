package com.example.codersquare.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private UUID id;

    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String userName;

    private String password;
}
