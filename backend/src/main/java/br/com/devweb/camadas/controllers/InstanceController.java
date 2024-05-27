package br.com.devweb.camadas.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/instance")
public class InstanceController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/say")
    public String getMethodName() {
        return "HII I am from Payment MS " + serverPort;
    }
}
