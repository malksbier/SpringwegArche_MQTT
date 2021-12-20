package de.springwegarche.webpage.Util.Mqtt.Util.Serives;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.springwegarche.webpage.Util.ConsolePrinter;
import de.springwegarche.webpage.Util.Mqtt.Models.DAO.InvertedTopic;
import de.springwegarche.webpage.Util.Mqtt.Util.InvalidTopicArrayList;
import de.springwegarche.webpage.Util.Mqtt.Util.Database.Topic;
import de.springwegarche.webpage.Util.Mqtt.Util.Database.Repositories.TopicRepository;
import de.springwegarche.webpage.Util.Security.SqlInjectionChecker;

@Service
public class TopicsService {
    final static String TAG = "[TopicsService] ";
    final static String SAFE_TAG = "[SAFE] ";
    final static String ERROR_TAG = "[ERROR] ";
    final static String INFO_TOPIC = "info";

    @Autowired
    private TopicRepository topicRepository;

    public TopicsService() {

    }

    public boolean updateNameSetByUser(long topicId, String nameSetByUser) {
        if(SqlInjectionChecker.isSafe(nameSetByUser)) {
            topicRepository.updateNameSetByUser(nameSetByUser,topicId);
            return true;
        }
        return false;
    }
    /**
     * @param topicId
     * Topic which value to set
     * @return
     */
    public String getFullMqttTopicString(long topicId) {
        String result = "";

        long parentId = topicId;
        while(parentId != 0) {
            Topic t = topicRepository.getById(parentId);
            result = t.getName() + "/" + result;

            if(parentId != t.getParent_id()) {
                parentId = t.getParent_id();
            } else {
                // the recived Topic is null and hasnt set the mainTopic.
                parentId = 0;
            }
        }

        if ((result != null) && (result.length() > 0)) {
            result = result.substring(0, result.length() - 1);
         }
        
        return result;
    }


    public ArrayList<InvertedTopic> getAllTopicsInverted() {
        ArrayList<InvertedTopic> result = new ArrayList<InvertedTopic>();

        List<Topic> maintopics = topicRepository.findAllTopicsWithNoParent();
        for(int i=0;i<maintopics.size();i++) {
            Topic maintopic = maintopics.get(i);
            InvertedTopic invertedTopic = new InvertedTopic(maintopic);
            invertedTopic = getInvertedTopicsForInvertedTopic(invertedTopic);
            result.add(invertedTopic);
        }
        
        return result;
    }

    /**
     * @param topic topic where to get the list for Topics from
     * @return List of topics seen from main Topic 
     */
    private InvertedTopic getInvertedTopicsForInvertedTopic(InvertedTopic topic) {

        List<Topic> subtopics = topicRepository.findAllTopicsWithParentID(topic.getId());
        for(int i=0;i<subtopics.size();i++) {
            Topic subSubTopic = subtopics.get(i);
            InvertedTopic invertedTopic = new InvertedTopic(subSubTopic);
            invertedTopic = getInvertedTopicsForInvertedTopic(invertedTopic);
            topic.addChildren(invertedTopic);
        }
        return topic;
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

            
            for(int j=0; j<splitTopics.size();j++) {
                String topicString = splitTopics.get(j);
                System.out.print(topicString + "    ");
            }
            System.out.println();
            

            // remove info tag
            if(splitTopics.get(splitTopics.size()-1).equals(INFO_TOPIC)) {
                splitTopics.remove(splitTopics.size()-1);
            }

            long lastTopicId = 0;
            // Check with DB
            for(int j=0; j<splitTopics.size();j++) {

                
                
                // if there is more then 1 topic in the list
                if(j != splitTopics.size()-1) {
                    // Send Base Topics without Info

                    // first has no parent
                    if(j==0) {
                        console.println(TAG + "first topic: " + splitTopics.get(j));
                        try {
                            List<Topic> first = topicRepository.findTopicWithNoParent(splitTopics.get(j));
                            if(first.size() == 1) {
                                console.println(TAG + "found first topic: " + splitTopics.get(j));
                                lastTopicId = first.get(0).getId();
                            } else if(first.size() < 1) {
                                console.println(TAG + "not found first topic: " + splitTopics.get(j));

                                console.println(TAG + SAFE_TAG + "first topic: " + splitTopics.get(j));
                                Topic t = topicRepository.save(new Topic(splitTopics.get(j)));
                                lastTopicId = t.getId();
                            } else {
                                console.println(TAG + ERROR_TAG + "found to many of first topic: name: " + splitTopics.get(j) + ", count: " + first.size());

                                //TODO remove to many main topics
                            }
                        } catch (Exception e) {
                            console.println(TAG + "first topic: " + splitTopics.get(j) + " has error");
                            console.println(TAG + e.toString());
                        }
                    }
                    // middle directories
                    if(j < splitTopics.size() && j!=0) {
                        console.println(TAG + "middle topic: " + splitTopics.get(j));

                        List<Topic> middle = topicRepository.findTopic(splitTopics.get(j), lastTopicId);
                        if(middle.size() == 1) {
                            console.println(TAG + "found middle topic: " + splitTopics.get(j));
                            lastTopicId = middle.get(0).getId();
                        } else if(middle.size() < 1) {
                            console.println(TAG + "not found middle topic: " + splitTopics.get(j));

                            console.println(TAG + SAFE_TAG + "middle topic: " + splitTopics.get(j));
                            Topic t = topicRepository.save(new Topic(splitTopics.get(j), lastTopicId));
                            lastTopicId = t.getId();
                        } else {
                            console.println(TAG + ERROR_TAG + "found to many of middle topic: name: " + splitTopics.get(j) + ", count: " + middle.size());

                            //TODO remove to many main topics
                        }
                    }
                }
                if(j == splitTopics.size() -1) {
                    console.println(TAG + "last topic: " + splitTopics.get(j));

                    List<Topic> last = topicRepository.findTopic(splitTopics.get(j), lastTopicId);
                        if(last.size() == 1) {
                            console.println(TAG + "found last topic: " + splitTopics.get(j));
                            lastTopicId = last.get(0).getId();
                        } else if(last.size() < 1) {
                            console.println(TAG + "not found last topic: " + splitTopics.get(j));

                            console.println(TAG + SAFE_TAG + "last topic: " + splitTopics.get(j));
                            Topic t = topicRepository.save(new Topic(splitTopics.get(j), lastTopicId, topic.getInfo()));
                            lastTopicId = t.getId();
                        } else {
                            console.println(TAG + ERROR_TAG + "found to many of last topic: name: " + splitTopics.get(j) + ", count: " + last.size());

                            //TODO remove to many main topics
                        }
                }
            }
        }
        
        return true;
    }
}
