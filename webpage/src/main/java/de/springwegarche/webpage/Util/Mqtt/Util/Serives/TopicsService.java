package de.springwegarche.webpage.Util.Mqtt.Util.Serives;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.springwegarche.webpage.Util.ConsolePrinter;
import de.springwegarche.webpage.Util.Mqtt.Util.InvalidTopicArrayList;
import de.springwegarche.webpage.Util.Mqtt.Util.Database.Topic;
import de.springwegarche.webpage.Util.Mqtt.Util.Database.Repositories.TopicRepository;

@Service
public class TopicsService {
    final static String TAG = "[GetAllTopicsClient] ";
    final static String SAFE_TAG = "[SAFE] ";
    final static String ERROR_TAG = "[ERROR] ";
    final static String INFO_TOPIC = "info";

    @Autowired
    private TopicRepository topicRepository;

    public TopicsService() {

    }

    public boolean addAndCheckTopics(InvalidTopicArrayList topics, ConsolePrinter console) {
        
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
                console.print(topicString + "    ");
            }
            console.println("");

            // Check with DB
            for(int j=0; j<splitTopics.size();j++) {
                if(j != splitTopics.size()-1) {
                    // Send Base Topics without Info

                    // first has no parent
                    if(j==0) {
                        console.println(TAG + "first topic: " + splitTopics.get(j));
                        try {
                            List<Topic> first = topicRepository.findFirstTopicWith(splitTopics.get(j));
                            if(first.size() == 1) {
                                console.println(TAG + "found first topic: " + splitTopics.get(j));
                            } else if(first.size() < 1) {
                                console.println(TAG + "not found first topic: " + splitTopics.get(j));

                                console.println(TAG + SAFE_TAG + "first topic: " + splitTopics.get(j));
                                topicRepository.save(new Topic(splitTopics.get(j)));
                            } else {
                                console.println(TAG + ERROR_TAG + "found to many of first topic: name: " + splitTopics.get(j) + ", count: " + first.size());

                                //TODO remove to many main topics
                            }
                        } catch (Exception e) {
                            console.println(TAG + "first topic: " + splitTopics.get(j) + " has error");
                            console.println(TAG + e.toString());
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
