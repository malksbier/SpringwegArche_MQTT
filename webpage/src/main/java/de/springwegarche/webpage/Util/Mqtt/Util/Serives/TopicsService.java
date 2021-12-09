package de.springwegarche.webpage.Util.Mqtt.Util.Serives;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.criteria.CriteriaBuilder.In;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.springwegarche.webpage.Util.Mqtt.Util.InvalidTopicArrayList;
import de.springwegarche.webpage.Util.Mqtt.Util.Database.Topic;
import de.springwegarche.webpage.Util.Mqtt.Util.Database.Repositories.TopicRepository;

@Service
public class TopicsService {
    final static String TAG = "[GetAllTopicsClient] ";
    final static String INFO_TOPIC = "info";

    @Autowired
    private TopicRepository topicRepository;

    public TopicsService() {

    }

    public boolean addAndCheckTopics(InvalidTopicArrayList topics) {
        
        // splice strings
        for(int i=0;i<topics.size();i++) {
            Topic topic = topics.get(i);

            ArrayList<String> splitTopics = new ArrayList<String>();

            String[] splitTopicsArray = topic.getName().split("/");
            for(int j=0; j<splitTopicsArray.length;j++) {
                splitTopics.add(splitTopicsArray[j]);
            }

            /*
            for(int j=0; j<splitTopics.size();j++) {
                String topicString = splitTopics.get(j);
                System.out.print(topicString + "    ");
            }
            System.out.println();
            */

            // remove info tag
            if(splitTopics.get(splitTopics.size()-1).equals(INFO_TOPIC)) {
                splitTopics.remove(splitTopics.size()-1);
            }

            for(int j=0; j<splitTopics.size();j++) {
                String topicString = splitTopics.get(j);
                System.out.print(topicString + "    ");
            }
            System.out.println();

            // Check with DB
            for(int j=0; j<splitTopics.size();j++) {
                if(j != splitTopics.size()-1) {
                    // Send Base Topics without Info

                    // first has no parent
                    if(j==0) {
                        try {
                            Collection<Topic> first = topicRepository.findFirstTopicWith(splitTopics.get(j));
                        } catch (Exception e) {
                            topicRepository.save(new Topic(splitTopics.get(j)));
                        }

                        
                        //System.out.println(first.toString());

                    } else {

                    }
                } else {
                    // Send Working Topic with Info
                }
            }
        }
        
        return true;
    }
}
