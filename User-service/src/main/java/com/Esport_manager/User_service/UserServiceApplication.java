package com.Esport_manager.User_service;

import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
		info = @Info(
				title = "User Service API",
				version = "1.0",
				description = "Módulo troncal del sistema encargado de resguardar los perfiles de competidores, validaciones de nicknames globales y datos personales."
		),
		servers = {
				@Server(url = "http://localhost:8080", description = "API Gateway")
		}
)
@EnableDiscoveryClient
@SpringBootApplication
public class UserServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}
}