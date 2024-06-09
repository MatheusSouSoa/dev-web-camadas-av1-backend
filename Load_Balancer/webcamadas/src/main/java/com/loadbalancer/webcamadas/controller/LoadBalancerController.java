package com.loadbalancer.webcamadas.controller;

import com.loadbalancer.webcamadas.service.LoadBalancerService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loadbalancer")
public class LoadBalancerController {

    @Autowired
    private LoadBalancerService loadBalancerService;

    @GetMapping("/**")
    public ResponseEntity<?> forwardGetRequest(HttpServletRequest request) {
        String path = request.getRequestURI().substring("/loadbalancer".length());
        String authToken = request.getHeader("Authorization");
        return loadBalancerService.forwardRequest(path, HttpMethod.GET, null, authToken);
    }

    @PostMapping(value = "/**", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> forwardPostRequest(HttpServletRequest request, @RequestBody String requestBody) {
        String path = request.getRequestURI().substring("/loadbalancer".length());
        if(path.equals("/api/auth/register") || path.equals("/api/auth/login")) {
            return loadBalancerService.forwardRequest(path, HttpMethod.POST, requestBody, "");
        } else {
            String authToken = request.getHeader("Authorization");
            return loadBalancerService.forwardRequest(path, HttpMethod.POST, requestBody, authToken);
        }
    }

    @PutMapping(value = "/**", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> forwardPutRequest(HttpServletRequest request, @RequestBody String requestBody) {
        String path = request.getRequestURI().substring("/loadbalancer".length());
        String authToken = request.getHeader("Authorization");
        return loadBalancerService.forwardRequest(path, HttpMethod.PUT, requestBody, authToken);
    }

    @DeleteMapping("/**")
    public ResponseEntity<?> forwardDeleteRequest(HttpServletRequest request) {
        String path = request.getRequestURI().substring("/loadbalancer".length());
        String authToken = request.getHeader("Authorization");
        System.out.println("Headers: " + authToken);
        return loadBalancerService.forwardRequest(path, HttpMethod.DELETE, null, authToken);
    }
}