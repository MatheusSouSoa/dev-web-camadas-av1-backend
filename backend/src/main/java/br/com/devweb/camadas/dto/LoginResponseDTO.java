package br.com.devweb.camadas.dto;

public record LoginResponseDTO(Long userId, String name, String email, String token) {
}
