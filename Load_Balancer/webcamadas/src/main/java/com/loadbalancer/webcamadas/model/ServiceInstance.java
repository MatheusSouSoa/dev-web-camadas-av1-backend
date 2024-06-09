package com.loadbalancer.webcamadas.model;

import lombok.Data;

@Data
public class ServiceInstance {
    private String host;
    private int port;

}