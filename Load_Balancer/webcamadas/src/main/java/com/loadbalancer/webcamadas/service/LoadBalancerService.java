package com.loadbalancer.webcamadas.service;

import com.loadbalancer.webcamadas.model.ServiceInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class LoadBalancerService {

    @Autowired
    private RegistryService registryService;

    private final AtomicInteger currentIndex = new AtomicInteger(0);

    public ResponseEntity<?> forwardRequest(String path, HttpMethod method, Object requestBody, String accessToken) {
        List<ServiceInstance> services = registryService.getAllServices();
        if (services.isEmpty()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No available services");
        }
        int index = currentIndex.getAndUpdate(i -> (i + 1) % services.size());
        ServiceInstance instance = services.get(index);
        String url = "http://" + instance.getHost() + ":" + instance.getPort() + path;
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        if(!Objects.equals(accessToken, "")) {
            headers.add("Authorization", accessToken);
        }

        HttpEntity<Object> requestEntity = new HttpEntity<>(requestBody, headers);

        return restTemplate.exchange(url, method, requestEntity, String.class);
    }
}