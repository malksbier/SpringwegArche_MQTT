package de.springwegarche.webpage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import de.springwegarche.webpage.Util.Mqtt.MqttApplication;
import de.springwegarche.webpage.Util.Mqtt.Util.Serives.TopicsService;

@SpringBootApplication
public class WebpageApplication {
	// DEPLOY mvn clean package
	// RUN ./mvnw spring-boot:run

	private static MqttApplication mqttApplication;
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(WebpageApplication.class, args);
		context.getBean(TopicsService.class);

		mqttApplication = new MqttApplication();
		mqttApplication.start();
	}
}
