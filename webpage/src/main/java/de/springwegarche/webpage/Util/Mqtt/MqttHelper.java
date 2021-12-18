package de.springwegarche.webpage.Util.Mqtt;

import java.util.UUID;

import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;

public class MqttHelper {

    static final String MqttServerIP = "tcp://springwegarche.de:1883";

    public static MqttAsyncClient getMqttClient(MqttCallback callback) throws MqttException {
        MqttAsyncClient result = new MqttAsyncClient(MqttServerIP, UUID.randomUUID().toString());

        result.setCallback(callback);

        return result;
    }
}
