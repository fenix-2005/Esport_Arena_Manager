package com.EsportManager.Notification_Service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class NotificationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationServiceApplication.class, args);
	}

}
