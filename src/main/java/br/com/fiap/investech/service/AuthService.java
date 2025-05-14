package br.com.fiap.investech.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.fiap.investech.model.Credentials;
import br.com.fiap.investech.model.User;
import br.com.fiap.investech.repository.UserRepository;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public User login(Credentials credentials, AuthenticationManager authenticationManager) {
        Authentication auth = new UsernamePasswordAuthenticationToken(
            credentials.getEmail(),
            credentials.getPassword()
        );
        Authentication authenticated = authenticationManager.authenticate(auth);
        return (User) authenticated.getPrincipal();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com email: " + email));
    }
}
