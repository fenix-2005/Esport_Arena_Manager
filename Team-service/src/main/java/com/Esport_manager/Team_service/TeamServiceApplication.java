package com.Esport_manager.Team_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
		info = @Info(
				title = "Team Service API",
				version = "1.0",
				description = "Módulo enfocado en el registro legal de clubes competitivos, control de capitanes y administración del roster oficial de jugadores."
		)
)
@EnableDiscoveryClient
@SpringBootApplication
public class TeamServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(TeamServiceApplication.class, args);
	}
}
