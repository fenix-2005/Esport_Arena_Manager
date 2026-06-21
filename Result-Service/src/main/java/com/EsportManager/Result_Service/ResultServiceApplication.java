package com.EsportManager.Result_Service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
		info = @Info(
				title = "Result Service API",
				version = "1.0",
				description = "Módulo especializado en el cierre oficial de planillas de juego, conteo de scores finales y envío de datos hacia los ránkings."
		)
)
@EnableDiscoveryClient
@SpringBootApplication
public class ResultServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(ResultServiceApplication.class, args);
	}
}
