package br.com.fiap.investech.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.fiap.investech.model.User;

@Service
public class TokenService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    public String generateToken(User user) {
        Algorithm algorithm = Algorithm.HMAC256(secret);

        return JWT.create()
            .withSubject(user.getEmail())
            .withIssuedAt(new Date())
            .withExpiresAt(Date.from(Instant.now().plus(expiration, ChronoUnit.MILLIS)))
            .sign(algorithm);
    }
}
