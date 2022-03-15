package de.springwegarche.webpage.Util.Mqtt.Clients;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.TabExpander;
import javax.transaction.Transactional;

import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import de.springwegarche.webpage.Util.ConsolePrinter;
import de.springwegarche.webpage.Util.Mqtt.Util.ApplicationContextUtil;
import de.springwegarche.webpage.Util.Mqtt.Util.TopicWriter;
import de.springwegarche.webpage.Util.Mqtt.Util.Database.Topic;
import de.springwegarche.webpage.Util.Mqtt.Util.Serives.TopicsService;


public class UpdateTopicsBasedOnTimeClient extends MqttAsyncClient{

    @Autowired
    TopicsService topicsService;

    private final String TAG = "[UpdateTopicsBasedOnTimeClient] ";
    private ConsolePrinter consolePrinter;
    private int checkTimeForUpdateTopicRatio;
    private int runCounter = 0;

    ArrayList<TopicLastChecked> topics;

    public UpdateTopicsBasedOnTimeClient(String serverURI, String clientId, ConsolePrinter printer, int checkTimeForUpdateTopicRatio) throws MqttException  {
        super(serverURI, clientId);

        this.checkTimeForUpdateTopicRatio = checkTimeForUpdateTopicRatio;

        ApplicationContext context = ApplicationContextUtil.getApplicationContext();
        this.topicsService = context.getBean(TopicsService.class);
        this.consolePrinter = printer;
        this.topics = new ArrayList<TopicLastChecked>();
    }

    public void checkForChanges() {
        if(runCounter >= checkTimeForUpdateTopicRatio) {
            getTopicsToCheck();
            runCounter = 0;
        }
        runCounter ++;
        
        checkTopicsForTime();
    }

    private void getTopicsToCheck() {
        long topicsAdded = 0;
        // getAll
        List<Topic> newTopics = topicsService.getAllTopicsWithTime();

        for(int i = 0;i<newTopics.size();i++) {
            Topic newTopic = newTopics.get(i);
            // check if we allready have them
            boolean found = false;
            for(int j = 0;j<topics.size();j++) {
                if(newTopic.hashCode() == topics.get(j).topic.hashCode()) {
                    found = true;
                    break;
                }
            }

            if(!found) {
                topics.add(new TopicLastChecked(newTopic));
                topicsAdded ++;
            }
        }
        if(topicsAdded > 0) {
            consolePrinter.println(TAG + "added " + topicsAdded + " to the review list.");
        }
    }
    
    private void checkTopicsForTime() {
        for(int i = 0;i<topics.size();i++) {
            TopicLastChecked tls = topics.get(i);
            
                LocalTime now = LocalTime.now();

                LocalTime start = LocalTime.parse(tls.topic.getStart());
                LocalTime stop = LocalTime.parse(tls.topic.getStop());

                if(now.isBefore(stop) && now.isAfter(start)) {
                    if(tls.currentValue != "ON") {
                        try {
                            writeToTopic(tls.topic.getId(), "ON");
                            tls.currentValue = "ON";
                        } catch (MqttException e) {
                            e.printStackTrace();
                        }
                    }
                    
                } else {
                    if(tls.currentValue != "OFF") {
                        try {
                            writeToTopic(tls.topic.getId(), "OFF");
                            tls.currentValue = "OFF";
                        } catch (MqttException e) {
                            e.printStackTrace();
                        }
                    }
                }
            
        }
    }
    private void writeToTopic(long topicId, String status) throws MqttPersistenceException, MqttException {
   
        String fullTopic = topicsService.getFullMqttTopicString(topicId);
    
        System.out.println(TAG + "writng to Topic: " + fullTopic + ", status: " + status);
      

        TopicWriter.writeToTopic(fullTopic,status, 1, false);
    
    }


    private class TopicLastChecked {
        public Topic topic;
        public String currentValue = "";
        TopicLastChecked(Topic t) {
            topic = t;
        }
    }


    
}
