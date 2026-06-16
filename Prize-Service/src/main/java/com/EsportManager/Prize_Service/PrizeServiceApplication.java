package com.EsportManager.Prize_Service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class PrizeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrizeServiceApplication.class, args);
	}

}
