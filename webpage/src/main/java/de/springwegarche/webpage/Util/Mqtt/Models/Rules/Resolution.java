package de.springwegarche.webpage.Util.Mqtt.Models.Rules;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;

import de.springwegarche.webpage.Util.Mqtt.Util.TopicWriter;
/**
 * When a rule apllies this class knows how to give the information to the topics that have to be updated
 */

@Entity
@Table(name = "Resolution")
public class Resolution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private long id;

    @OneToMany(mappedBy = "resolution", cascade = CascadeType.ALL)
    private Set<TopicResolutionPair> topicsToChange = new HashSet<TopicResolutionPair>();

    void writeToTopics(boolean dependencySolution) throws MqttPersistenceException, MqttException {
        for (TopicResolutionPair topicResolutionPair : topicsToChange) {
            String message = topicResolutionPair.getMessage(dependencySolution);

            
            TopicWriter.writeToTopic(topicResolutionPair.getTopic().getTopicName() , message, 2, false);
        }
    }
}
