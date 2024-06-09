package br.com.devweb.camadas.services;

import br.com.devweb.camadas.dto.LoginRequestDTO;
import br.com.devweb.camadas.dto.LoginResponseDTO;
import br.com.devweb.camadas.dto.RegisterUserRequestDTO;
import br.com.devweb.camadas.dto.RegisterUserResponseDTO;
import br.com.devweb.camadas.infra.security.TokenService;
import br.com.devweb.camadas.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService  {

    @Autowired
    private UserService _userService;

    @Autowired
    private TokenService _tokenService;

    @Autowired
    PasswordEncoder passwordEncoder;

    public ResponseEntity<?> authenticateUser(LoginRequestDTO loginRequest) {

        User user = _userService.getUserByEmail(loginRequest.email().toLowerCase());

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
        }

        if (!passwordEncoder.matches( loginRequest.password(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Senha incorreta.");
        }

        String token = _tokenService.generateToken(user);

        return ResponseEntity.ok(new LoginResponseDTO(user.getId(),user.getName(), user.getEmail(), token));

    }

    public ResponseEntity<?> registerUser(RegisterUserRequestDTO body) {

        System.out.println(body);

        if(_userService.getUserByEmail(body.email()) != null){
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("user ja cadastrado");
        }

        User newUser = new User(body.name(), body.email(), passwordEncoder.encode(body.password()));

        newUser = _userService.saveUser(newUser);

        if (newUser != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(new RegisterUserResponseDTO(newUser.getId(), newUser.getName(), newUser.getEmail()));
        }

        return ResponseEntity.badRequest().build();

    }

}
