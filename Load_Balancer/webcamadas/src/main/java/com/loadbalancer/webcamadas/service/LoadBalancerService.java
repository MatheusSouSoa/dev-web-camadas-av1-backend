package com.loadbalancer.webcamadas.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loadbalancer.webcamadas.model.ServiceInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class LoadBalancerService {

    @Autowired
    private RegistryService registryService;

    private final AtomicInteger currentIndex = new AtomicInteger(0);
    private static final Logger logger = LoggerFactory.getLogger(LoadBalancerService.class);

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

        logger.info("Forwarding request to URL: " + url);
        logger.info("Method: " + method);
        logger.info("Request Body: " + requestBody);
        logger.info("Headers: " + headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(url, method, requestEntity, String.class);
            logger.info("Response Status: " + response.getStatusCode());
            logger.info("Response Body: " + response.getBody());

            if (response.getHeaders().getContentType().includes(MediaType.APPLICATION_JSON)) {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode jsonResponse = mapper.readTree(response.getBody());

                return ResponseEntity.status(response.getStatusCode()).body(jsonResponse);
            } else {
                logger.error("Response is not in JSON format");
                return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
            }
        } catch (HttpStatusCodeException e) {
            logger.error("Error during request forwarding: " + e.getMessage());
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        } catch (Exception e) {
            logger.error("Unexpected error during request forwarding: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}