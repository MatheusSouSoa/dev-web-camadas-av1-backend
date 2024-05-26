package br.com.devweb.camadas.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Long id;
    private String name;
    private String email;
    private String password;

    public User(String name, String email, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

}
