package com.EsportManager.Ranking_Service;

import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
		info = @Info(
				title = "Ranking Service API",
				version = "1.0",
				description = "Servicio encargado del procesamiento matemático de tablas de posiciones, acumulación de puntos por victoria y clasificaciones globales."
		),
		servers = {
				@Server(url = "http://localhost:8080", description = "API Gateway")
		}
)
@EnableDiscoveryClient
@SpringBootApplication
public class RankingServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(RankingServiceApplication.class, args);
	}
}
