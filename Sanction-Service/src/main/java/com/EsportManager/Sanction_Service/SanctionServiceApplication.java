package com.EsportManager.Sanction_Service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class SanctionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SanctionServiceApplication.class, args);
	}

}
