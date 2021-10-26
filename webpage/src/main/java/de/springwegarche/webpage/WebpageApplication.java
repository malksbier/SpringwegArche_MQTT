package de.springwegarche.webpage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebpageApplication {
	// DEPLOY mvn clean package
	// RUN ./mvnw spring-boot:run
	public static void main(String[] args) {
		SpringApplication.run(WebpageApplication.class, args);
	}

}
