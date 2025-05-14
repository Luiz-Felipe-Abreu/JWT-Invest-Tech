package br.com.fiap.investech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import br.com.fiap.investech.model.Credentials;
import br.com.fiap.investech.model.User;
import br.com.fiap.investech.repository.UserRepository;
import br.com.fiap.investech.service.AuthService;
import br.com.fiap.investech.service.TokenService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Endpoint de login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Credentials credentials) {
        try {
            User user = authService.login(credentials); // autentica
            String token = tokenService.generateToken(user); // gera token
            return ResponseEntity.ok().body(token);
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Credenciais inválidas.");
        }
    }

    // Endpoint de registro
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Usuário já cadastrado.");
        }

        // Criptografa a senha antes de salvar
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);
        return ResponseEntity.ok(savedUser);
    }
}
