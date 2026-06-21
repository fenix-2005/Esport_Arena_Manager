package com.EsportManager.Registration_Service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
		info = @Info(
				title = "Registration Service API",
				version = "1.0",
				description = "Microservicio core encargado del procesamiento de postulaciones, validación distribuidas de cupos de torneo y flujos de aceptación."
		)
)
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class RegistrationServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(RegistrationServiceApplication.class, args);
	}
}
