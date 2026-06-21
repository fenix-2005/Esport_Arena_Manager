package com.EsportManager.Notification_Service;

import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
		info = @Info(
				title = "Notification Service API",
				version = "1.0",
				description = "Servicio de mensajería automatizada interno para alertar cambios en fixtures, estados de inscripción o alertas disciplinarias."
		),
		servers = {
				@Server(url = "http://localhost:8080", description = "API Gateway")
		}
)
@EnableDiscoveryClient
@SpringBootApplication
public class NotificationServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(NotificationServiceApplication.class, args);
	}
}
