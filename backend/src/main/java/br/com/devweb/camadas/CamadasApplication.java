package br.com.devweb.camadas;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Rachao", version = "1.0", description = "API`s rachao"))
public class CamadasApplication {

	public static void main(String[] args) {
		SpringApplication.run(CamadasApplication.class, args);
	}

}
