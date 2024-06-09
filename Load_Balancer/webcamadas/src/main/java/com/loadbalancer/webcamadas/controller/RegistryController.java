package com.loadbalancer.webcamadas.controller;

import com.loadbalancer.webcamadas.model.ServiceInstance;
import com.loadbalancer.webcamadas.service.RegistryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/registry")
public class RegistryController {

    @Autowired
    private RegistryService registryService;

    @PostMapping("/register")
    public void registerService(@RequestBody ServiceInstance serviceInstance) {
        registryService.registerService(serviceInstance);
    }

    @GetMapping("/services")
    public List<ServiceInstance> getAllServices() {
        return registryService.getAllServices();
    }
}