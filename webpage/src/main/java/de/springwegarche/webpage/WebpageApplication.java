package de.springwegarche.webpage;

import java.util.ArrayList;

import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.springwegarche.webpage.Util.Mqtt.MqttApplication;
import de.springwegarche.webpage.Util.Mqtt.MqttCallbackTest;
import de.springwegarche.webpage.Util.Mqtt.MqttHelper;

@SpringBootApplication
public class WebpageApplication {
	// DEPLOY mvn clean package
	// RUN ./mvnw spring-boot:run

	private static MqttApplication mqttApplication;
	public static void main(String[] args) {
		SpringApplication.run(WebpageApplication.class, args);
		mqttApplication = new MqttApplication();
		mqttApplication.start();
	}
}
