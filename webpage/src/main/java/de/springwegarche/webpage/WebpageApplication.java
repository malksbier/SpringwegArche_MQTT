package de.springwegarche.webpage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import de.springwegarche.webpage.Util.Mqtt.MqttApplication;
import de.springwegarche.webpage.Util.Mqtt.Clients.BlingThread;
import de.springwegarche.webpage.Util.Mqtt.Util.TopicWriter;
import de.springwegarche.webpage.Util.Mqtt.Util.Serives.TopicsService;

@SpringBootApplication
public class WebpageApplication {
	// DEPLOY mvn clean package
	// RUN ./mvnw spring-boot:run

	private static MqttApplication mqttApplication;
	public static void main(String[] args) throws InterruptedException {
		ConfigurableApplicationContext context = SpringApplication.run(WebpageApplication.class, args);
		context.getBean(TopicsService.class);

		
/* 
		while(true) {
			BlingThread start = new BlingThread("malksbier/Anton´s Zimmer/Terrarien/Allgemein/relais/8", true);
			BlingThread stopU = new BlingThread("malksbier/Anton´s Zimmer/Terrarien/Allgemein/relais/7", false);
			start.start();
			stopU.start();
			Thread.sleep(200);
			BlingThread stop = new BlingThread("malksbier/Anton´s Zimmer/Terrarien/Allgemein/relais/8", false);
			BlingThread startU = new BlingThread("malksbier/Anton´s Zimmer/Terrarien/Allgemein/relais/7", true);
			stop.start();
			startU.start();
			Thread.sleep(200);

		}

		try {int x=0;
            while(true){
            
            System.out.println("test "+x);
			new Thread.
            TopicWriter.writeToTopic("malksbier/Anton´s Zimmer/Terrarien/Allgemein/relais/8","ON", 1, false);
            TopicWriter.writeToTopic("malksbier/Anton´s Zimmer/Terrarien/Allgemein/relais/7","OFF", 1, false);
            Thread.sleep(2);
            TopicWriter.writeToTopic("malksbier/Anton´s Zimmer/Terrarien/Allgemein/relais/8","OFF", 1, false);
            TopicWriter.writeToTopic("malksbier/Anton´s Zimmer/Terrarien/Allgemein/relais/7","ON", 1, false);
            Thread.sleep(2);
            x++;
        }
            
            //malksbier/Anton´s Zimmer/Terrarien/Allgemein/relais/8
        } catch (Exception e) {
            //TODO: handle exception
        }
		*/
		//mqttApplication = new MqttApplication();
		//mqttApplication.start();
	}
}
