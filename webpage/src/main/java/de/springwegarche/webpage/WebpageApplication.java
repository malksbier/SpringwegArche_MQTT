package de.springwegarche.webpage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.springwegarche.webpage.Util.Mqtt.MqttApplication;

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
	/*
	private ArrayList<MqttAsyncClient> mqttClients;
	
	WebpageApplication() {
		mqttClients = new ArrayList<MqttAsyncClient>();

		try {
			mqttClients.add(MqttHelper.getMqttClient(new MqttCallbackTest()));
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			IMqttToken token = mqttClients.get(0).connect();
			token.waitForCompletion();

			mqttClients.get(0).subscribe("#", 0);
		} catch (MqttSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	*/

}
