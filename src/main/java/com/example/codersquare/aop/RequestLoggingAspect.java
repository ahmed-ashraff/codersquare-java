package com.example.codersquare.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
@Component
public class RequestLoggingAspect {
    private final Logger logger = LoggerFactory.getLogger(RequestLoggingAspect.class);

    @Before("execution(* com.example.codersquare.controller.*.*(..))")
    public void logBeforeRequest(JoinPoint joinPoint) {
        logger.info("Request received for method: {}", joinPoint.getSignature().getName());
    }
}
