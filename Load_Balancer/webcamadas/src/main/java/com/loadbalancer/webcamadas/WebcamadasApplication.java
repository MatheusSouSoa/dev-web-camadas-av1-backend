package com.loadbalancer.webcamadas;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Load Balancer", version = "1.0", description = "API`s loadbalancer"))
public class WebcamadasApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebcamadasApplication.class, args);
	}

}
