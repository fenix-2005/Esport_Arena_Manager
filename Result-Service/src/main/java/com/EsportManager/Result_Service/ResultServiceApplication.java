package com.EsportManager.Result_Service;

import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.cloud.openfeign.EnableFeignClients;

@OpenAPIDefinition(
		info = @Info(
				title = "Result Service API",
				version = "1.0",
				description = "Módulo especializado en el cierre oficial de planillas de juego, conteo de scores finales y envío de datos hacia los ránkings."
		),
		servers = {
				@Server(url = "http://localhost:8080", description = "API Gateway")
		}
)
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
public class ResultServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(ResultServiceApplication.class, args);
	}
}
