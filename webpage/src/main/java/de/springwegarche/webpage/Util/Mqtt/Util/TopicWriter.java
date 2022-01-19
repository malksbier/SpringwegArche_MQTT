package de.springwegarche.webpage.Util.Mqtt.Util;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;

import de.springwegarche.webpage.Util.Mqtt.MqttApplication;
import de.springwegarche.webpage.Util.Mqtt.Util.Database.Topic;
import de.springwegarche.webpage.Util.Mqtt.Util.Serives.TopicsService;



@Service
public class TopicWriter {

    @Autowired 
    TopicsService topicsService;

    public static void writeToTopic(String fullLengthTopic, String message, int qos, boolean retained) throws MqttPersistenceException, MqttException {
        MqttClient client = getClient();
        client.publish(fullLengthTopic, message.getBytes(), qos, retained);
        client.disconnect();
    }
    public void writeToTopic(Topic topic, String message, int qos, boolean retained) throws MqttPersistenceException, MqttException {
        MqttClient client = getClient();

        String topicString = topicsService.getFullMqttTopicString(topic.getId());

        client.publish(topicString, message.getBytes(), qos, retained);
        client.disconnect();
    }
    private static MqttClient getClient() throws MqttException {
        MqttClient client = new MqttClient(MqttApplication.serverURI, MqttApplication.clientId);
        client.connect();
        return client;
    }
}
