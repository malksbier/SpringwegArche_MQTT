package de.springwegarche.webpage.Util.Mqtt.Clients;

import java.util.ArrayList;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import de.springwegarche.webpage.Util.ConsolePrinter;
import de.springwegarche.webpage.Util.Mqtt.Util.InvalidTopicArrayList;
import de.springwegarche.webpage.Util.Mqtt.Util.Database.Topic;
import de.springwegarche.webpage.Util.Mqtt.Util.Serives.TopicsService;

public class GetAllTopicsClient extends MqttAsyncClient{
    private final String TAG = "[GetAllTopicsClient] ";
    private ConsolePrinter consolePrinter;

    private InvalidTopicArrayList topics;

    public GetAllTopicsClient(String serverURI, String clientId, ConsolePrinter printer) throws MqttException {
        super(serverURI, clientId);

        this.consolePrinter = printer;
        topics = new InvalidTopicArrayList();
        
        this.setCallback(new GetAllTopicsClientCallback());

        IMqttToken token = this.connect();
        token.waitForCompletion();
        this.subscribe("#", 2);
    }

    public void interpreteTopics() {
        if(topics.size() > 0) {
            TopicsService.addAndCheckTopics(topics);
            topics.clear();
        }
    }

    private class GetAllTopicsClientCallback implements MqttCallback {

        @Override
        public void connectionLost(Throwable e) {
            if(e.toString().contains("32109")) {

            } else {
                consolePrinter.println(TAG + "connection lost" + e.toString());
            }
            
        }

        @Override
        public void deliveryComplete(IMqttDeliveryToken e) {
            
        }

        @Override
        public void messageArrived(String topic, MqttMessage message) throws Exception {
            //consolePrinter.println(TAG + "message arrived");
            //consolePrinter.println(TAG + "topic: " + topic);
            //consolePrinter.println(TAG + "MqttMessage: " + message.toString());

            topics.add(new Topic(topic,message.toString()));
        }

    }
    
}
