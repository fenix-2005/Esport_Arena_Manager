package com.EsportManager.Match_Service;

import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.cloud.openfeign.EnableFeignClients;

@OpenAPIDefinition(
		info = @Info(
				title = "Match Service API",
				version = "1.0",
				description = "Microservicio para la gestión de fixtures, programación de enfrentamientos en vivo y control de rondas competitivas."
		),
		servers = {
				@Server(url = "http://localhost:8080", description = "API Gateway")
		}
)
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class MatchServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(MatchServiceApplication.class, args);
	}
}
