package br.com.devweb.camadas.controllers;

import br.com.devweb.camadas.dto.LoginRequestDTO;
import br.com.devweb.camadas.dto.RegisterUserRequestDTO;
import br.com.devweb.camadas.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService _authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO body) {
        return _authService.authenticateUser(body);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterUserRequestDTO body) {
        return _authService.registerUser(body);
    }
}