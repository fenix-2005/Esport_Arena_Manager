package com.EsportManager.Prize_Service;

import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
		info = @Info(
				title = "Prize Service API",
				version = "1.0",
				description = "Módulo enfocado en la estructuración de pozos de recompensa por torneo y la adjudicación formal de premios a los ganadores."
		),
		servers = {
				@Server(url = "http://localhost:8080", description = "API Gateway")
		}
)
@EnableDiscoveryClient
@SpringBootApplication
public class PrizeServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(PrizeServiceApplication.class, args);
	}
}
