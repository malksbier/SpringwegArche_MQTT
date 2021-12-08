package de.springwegarche.webpage.Util.Mqtt.Util.Serives;

import de.springwegarche.webpage.Util.Mqtt.Util.InvalidTopicArrayList;
import de.springwegarche.webpage.Util.Mqtt.Util.Database.Topic;

public class TopicsService {
    final static String TAG = "[GetAllTopicsClient] ";
    public static boolean addAndCheckTopics(InvalidTopicArrayList topics) {
        
        // splice strings
        for(int i=0;i<topics.size();i++) {
            Topic topic = topics.get(i);

            String[] splitTopics = topic.getName().split("/");
            /*
            for(int j=0; j<splitTopics.length;j++) {
                String topicString = splitTopics[j];
                System.out.print(topicString + "    ");
            }
            */
            
        }
        System.out.println();
        return true;
    }
}
