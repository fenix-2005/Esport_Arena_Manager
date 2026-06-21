package com.Esport_manager.Tournament_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
		info = @Info(
				title = "Tournament Service API",
				version = "1.0",
				description = "Módulo de gestión de ligas estructurales, encargado de configurar cupos de torneos, cronogramas y modalidades de juego."
		)
)
@EnableDiscoveryClient
@SpringBootApplication
public class TournamentServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(TournamentServiceApplication.class, args);
	}
}