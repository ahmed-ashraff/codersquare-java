package com.example.codersquare.service;

import org.aspectj.lang.JoinPoint;

public interface LoggingService {
    void logRequest(JoinPoint joinPoint);
}
