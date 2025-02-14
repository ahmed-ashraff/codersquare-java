package com.example.codersquare.service;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


@Service
public class LoggingServiceImp implements LoggingService {
    private final Logger logger = LoggerFactory.getLogger(LoggingService.class);

    @Override
    public void logRequest(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        logger.info("Request received - Method: {}, Endpoint: {}, Request Type: {}",
                joinPoint.getSignature().getName(),
                request.getRequestURI(),
                request.getMethod());
    }
}
