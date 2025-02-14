package com.example.codersquare.aop;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class RequestLoggingAspect {
    private final Logger logger = LoggerFactory.getLogger(RequestLoggingAspect.class);

    @Before("execution(* com.example.codersquare.controller.*.*(..))")
    public void logBeforeRequest(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        logger.info("Request received - Method: {}, Endpoint: {}, Request Type: {}",
                joinPoint.getSignature().getName(),
                request.getRequestURI(),
                request.getMethod());
    }
}
