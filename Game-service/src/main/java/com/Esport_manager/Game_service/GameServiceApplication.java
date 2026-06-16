package com.Esport_manager.Game_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class GameServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GameServiceApplication.class, args);
	}

}
