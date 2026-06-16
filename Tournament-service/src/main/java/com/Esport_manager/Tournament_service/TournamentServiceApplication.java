package com.Esport_manager.Tournament_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class TournamentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TournamentServiceApplication.class, args);
	}

}
