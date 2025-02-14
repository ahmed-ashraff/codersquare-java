package com.example.codersquare.middleware;

import com.example.codersquare.service.LoggingService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RequestLoggingAspect {
    private final LoggingService loggingService;

    @Autowired
    public RequestLoggingAspect(LoggingService loggingService) {
        this.loggingService = loggingService;
    }

    @Before("execution(* com.example.codersquare.controller.*.*(..))")
    public void logBeforeRequest(JoinPoint joinPoint) {
        loggingService.logRequest(joinPoint);
    }
}
