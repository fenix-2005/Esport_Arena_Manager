package com.EsportManager.Sanction_Service;

import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.cloud.openfeign.EnableFeignClients;

@OpenAPIDefinition(
		info = @Info(
				title = "Sanction Service API",
				version = "1.0",
				description = "Tribunal de disciplina encargado de auditar comportamientos tóxicos, emitir suspensiones cronometradas y vetar temporalmente inscripciones."
		),
		servers = {
				@Server(url = "http://localhost:8080", description = "API Gateway")
		}
)
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
public class SanctionServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(SanctionServiceApplication.class, args);
	}
}
