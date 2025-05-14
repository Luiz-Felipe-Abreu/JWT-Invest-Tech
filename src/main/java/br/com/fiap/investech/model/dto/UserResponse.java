package br.com.fiap.investech.model.dto;

import br.com.fiap.investech.model.UserRole;

public record UserResponse(Long id, String email, UserRole role) {}
