package de.springwegarche.webpage.Util.Mqtt;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MqttCallbackTest implements MqttCallback{
    private final String TAG = "MqttCallbackTest";

    @Override
    public void connectionLost(Throwable er) {
        System.out.println(TAG + ": lost Connection, " + er.getMessage());
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        try {
            printMessage(token.getTopics().toString(),token.getMessage().toString(),"deliveryComplete");
        } catch (Exception e) {
            printMessage(token.getTopics().toString(),"NONE","deliveryComplete");
        }
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        printMessage(topic,message.toString(),"Message arrived");
    }

    private void printMessage(String topic, String message, String textInfo) {
        System.out.println(TAG + ": " + textInfo  + ": Topic:" + topic + ", Message: " + message);
    }
    
}
