package de.springwegarche.webpage.Util.Mqtt;

import java.util.ArrayList;

import org.eclipse.paho.client.mqttv3.MqttAsyncClient;

import de.springwegarche.webpage.Util.ConsolePrinter;
import de.springwegarche.webpage.Util.Mqtt.Clients.GetAllTopicsClient;
import de.springwegarche.webpage.Util.Mqtt.Clients.UpdateTopicsBasedOnTimeClient;
import de.springwegarche.webpage.Util.Mqtt.Util.TopicWriter;

public class MqttApplication extends Thread {
    private final String TAG = "[MqttApplication] ";
    private final long LoopDelayMillis = 50;
    private ConsolePrinter consolePrinter = new ConsolePrinter(true);
    private boolean running = true;

    public static String serverURI = "tcp://192.168.2.112:1883";
    public static String clientId = "MqttApplication";

    private ArrayList<MqttAsyncClient> mqttClients;

    public MqttApplication() {
        this.mqttClients = new ArrayList<MqttAsyncClient>();
    }

    public boolean getRunning() {
        return this.running;
    }

    private void setRunning(boolean running) {
        this.running = running;
    }
    private void starting() { 
        try {
            mqttClients.add(new GetAllTopicsClient(serverURI,clientId,consolePrinter));
            //mqttClients.add(new UpdateTopicsBasedOnTimeClient(serverURI,clientId,consolePrinter,0));
        } catch (Exception e) {
            consolePrinter.println(TAG + "failed starting");
            consolePrinter.println(TAG + e);
            setRunning(false);
        }
    }

    private void loop() throws InterruptedException {
       
        Thread.sleep(LoopDelayMillis);
        
        //consolePrinter.println(TAG + "loop");
      

 
        GetAllTopicsClient getAll = (GetAllTopicsClient)mqttClients.get(0);
        getAll.interpreteTopics();

        UpdateTopicsBasedOnTimeClient time = (UpdateTopicsBasedOnTimeClient)mqttClients.get(1);
        time.checkForChanges();
        
    }
    public void run() {
        consolePrinter.println(TAG + "started");
        starting();
        while(running) {
            try {
                loop();
            } catch (Exception e) {
                consolePrinter.println(TAG + "got Exception");
                e.printStackTrace();
                setRunning(false);
            }
        }
        consolePrinter.println(TAG + "stoped");
    }
}
