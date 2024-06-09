package br.com.devweb.camadas;

import br.com.devweb.camadas.data.ServiceInstance;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import java.util.logging.Logger;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Rachao", version = "1.0", description = "API`s rachao"))
//@EnableDiscoveryClient
public class CamadasApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CamadasApplication.class, args);
	}

	public void run(String... args) throws Exception {
		registerWithLoadBalancer();
	}

	@Value("${server.port}")
	private int loadBalancerPort;
	Logger logger = Logger.getLogger(getClass().getName());

	private void registerWithLoadBalancer() {
		String loadBalancerUrl = "http://localhost:8080/registry/register";
		ServiceInstance instance = new ServiceInstance();
		instance.setHost("localhost");
		instance.setPort(loadBalancerPort);
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForObject(loadBalancerUrl, instance, Void.class);
		logger.info("Registered with load balancer successfully!");
		logger.info("Instance details: " + instance);
	}

}
