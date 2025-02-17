package com.example.codersquare.config;

import com.example.codersquare.exception.UserNotFoundException;
import com.example.codersquare.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final UserRepository userRepository;

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findUsersByUserName(username).
                orElseThrow(() -> new UserNotFoundException("User not found"));
    }
}
