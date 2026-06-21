package com.Esport_manager.Game_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
		info = @Info(
				title = "Game Service API",
				version = "1.0",
				description = "Módulo encargado del catálogo oficial de videojuegos soportados, modalidades de juego y configuraciones por equipo."
		)
)
@EnableDiscoveryClient
@SpringBootApplication
public class GameServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(GameServiceApplication.class, args);
	}
}
