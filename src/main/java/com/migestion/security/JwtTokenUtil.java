package com.migestion.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenUtil {

    private static final String SECRET_KEY = "contraseña_segura";
    private static final long EXPIRATION_TIME = 10 * 24 * 60 * 60 * 1000L;

    private final Algorithm algorithm = Algorithm.HMAC512(SECRET_KEY);

    public String generateToken(String subject) {
        String token = JWT.create()
                .withSubject(subject)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(algorithm);

        return "Bearer " + token;
    }


    public boolean verify(String token) {
        try {
            String cleanedToken = token.replace("Bearer ", "");
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(cleanedToken);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public String getSubject(String token) {
        String cleanedToken = token.replace("Bearer ", "");
        DecodedJWT jwt = JWT.require(algorithm).build().verify(cleanedToken);
        return jwt.getSubject();
    }
}