package br.com.fiap.investech.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.fiap.investech.model.Credentials;
import br.com.fiap.investech.model.User;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    public User login(Credentials credentials) {
        Authentication auth = new UsernamePasswordAuthenticationToken(
            credentials.getEmail(),
            credentials.getPassword()
        );
        Authentication authenticated = authenticationManager.authenticate(auth);
        return (User) authenticated.getPrincipal(); // cast seguro se User implementar UserDetails
    }
}
