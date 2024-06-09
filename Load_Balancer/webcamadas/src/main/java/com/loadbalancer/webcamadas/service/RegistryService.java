package com.loadbalancer.webcamadas.service;

import com.loadbalancer.webcamadas.model.ServiceInstance;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RegistryService {
    private final List<ServiceInstance> serviceInstances = new ArrayList<>();

    public void registerService(ServiceInstance serviceInstance) {
        serviceInstances.add(serviceInstance);
    }

    public List<ServiceInstance> getAllServices() {
        return serviceInstances;
    }
}