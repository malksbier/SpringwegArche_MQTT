package de.springwegarche.webpage.Util.Mqtt.Util;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;

import de.springwegarche.webpage.Util.Mqtt.MqttApplication;

public class TopicWriter {
    public static void writeToTopic(String topic, String message, int qos, boolean retained) throws MqttPersistenceException, MqttException {
        MqttClient client = getClient();
        client.publish(topic, message.getBytes(), qos, retained);
        client.disconnect();
    }
    private static MqttClient getClient() throws MqttException {
        MqttClient client = new MqttClient(MqttApplication.serverURI, MqttApplication.clientId);
        client.connect();
        return client;
    }
}
