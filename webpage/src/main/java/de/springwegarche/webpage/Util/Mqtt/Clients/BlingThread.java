package de.springwegarche.webpage.Util.Mqtt.Clients;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;

import de.springwegarche.webpage.Util.Mqtt.Util.TopicWriter;

public class BlingThread extends Thread{
    public BlingThread(String topic, boolean start) {
        this.topic = topic;
        this.start = start;
    }

    private boolean start;
    private String topic;

    
    @Override
    public void run() {
        String message = "";
        if(start) {
            message = "ON";
        } else {
            message = "OFF";
        }

        try {
            TopicWriter.writeToTopic(topic, message, 0, false);
        } catch (MqttPersistenceException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MqttException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
}
