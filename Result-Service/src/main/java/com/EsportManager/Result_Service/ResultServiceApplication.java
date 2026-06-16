package com.EsportManager.Result_Service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class ResultServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResultServiceApplication.class, args);
	}

}
