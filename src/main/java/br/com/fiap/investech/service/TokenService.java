package br.com.fiap.investech.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import br.com.fiap.investech.model.User;
import br.com.fiap.investech.repository.UserRepository;

@Service
public class TokenService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    @Autowired
    private UserRepository userRepository;

    public String generateToken(User user) {
        Algorithm algorithm = Algorithm.HMAC256(secret);

        return JWT.create()
            .withSubject(user.getEmail())
            .withIssuedAt(new Date())
            .withExpiresAt(Date.from(Instant.now().plus(expiration, ChronoUnit.MILLIS)))
            .sign(algorithm);
    }

    public User validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            String email = decodedJWT.getSubject();

            return userRepository.findByEmail(email).orElse(null);
        } catch (JWTVerificationException e) {
            return null; // Token inválido
        }
    }
}
