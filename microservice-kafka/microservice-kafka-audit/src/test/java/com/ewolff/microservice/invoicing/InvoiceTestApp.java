package com.ewolff.microservice.audit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuditTestApp {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(AuditTestApp.class);
		app.setAdditionalProfiles("test");
		app.run(args);
	}

}
