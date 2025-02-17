package com.example.codersquare.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class JwtTokenProvider {
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    public String generateJWT(String username, UUID userId) {
        return JWT.create()
                .withSubject(username)
                .withClaim("userId", userId.toString())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + jwtExpiration))
                .sign(Algorithm.HMAC256(jwtSecret));
    }

    public boolean validateJWT(String jwt) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(jwtSecret)).build();
            verifier.verify(jwt);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }

    public String extractUsernameFromJWT(String jwt) {
        DecodedJWT decodedJWT = JWT.decode(jwt);
        return decodedJWT.getSubject();
    }

    public UUID extractUserIdFromJWT(String jwt) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(jwtSecret)).build();

            DecodedJWT decodedJWT = verifier.verify(jwt);

            return UUID.fromString(decodedJWT.getClaim("userId").asString());
        } catch (JWTVerificationException e) {
            throw new JWTVerificationException("Invalid JWT");
        }
    }
}
