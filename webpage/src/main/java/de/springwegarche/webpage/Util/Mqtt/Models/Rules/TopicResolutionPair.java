package de.springwegarche.webpage.Util.Mqtt.Models.Rules;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;

import de.springwegarche.webpage.Util.Mqtt.Util.Database.Topic;

/**
 * for each Topic knows what to write
 */

@Entity
@Table(name = "topic_resolution_pair")
public class TopicResolutionPair {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private long id;
    @ManyToOne(fetch = FetchType.EAGER) 
    private Resolution resolution;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(updatable = true, unique = false, nullable = false)
    private Topic topic;
    @Column(updatable = true, unique = false, nullable = false)
    private String onTrue;
    @Column(updatable = true, unique = false, nullable = false)
    private String onFalse;

    String getMessage(boolean dependencySolution) {
        if(dependencySolution) {
            return onTrue;
        } else {
            return onFalse;
        }
    }


    public TopicResolutionPair() {
    }

    public TopicResolutionPair(long id, Resolution resolution, Topic topic, String onTrue, String onFalse) {
        this.id = id;
        this.resolution = resolution;
        this.topic = topic;
        this.onTrue = onTrue;
        this.onFalse = onFalse;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Resolution getResolution() {
        return this.resolution;
    }

    public void setResolution(Resolution resolution) {
        this.resolution = resolution;
    }

    public Topic getTopic() {
        return this.topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public String getOnTrue() {
        return this.onTrue;
    }

    public void setOnTrue(String onTrue) {
        this.onTrue = onTrue;
    }

    public String getOnFalse() {
        return this.onFalse;
    }

    public void setOnFalse(String onFalse) {
        this.onFalse = onFalse;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof TopicResolutionPair)) {
            return false;
        }
        TopicResolutionPair topicResolutionPair = (TopicResolutionPair) o;
        return id == topicResolutionPair.id && Objects.equals(resolution, topicResolutionPair.resolution) && Objects.equals(topic, topicResolutionPair.topic) && Objects.equals(onTrue, topicResolutionPair.onTrue) && Objects.equals(onFalse, topicResolutionPair.onFalse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, resolution, topic, onTrue, onFalse);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", resolution='" + getResolution() + "'" +
            ", topic='" + getTopic() + "'" +
            ", onTrue='" + getOnTrue() + "'" +
            ", onFalse='" + getOnFalse() + "'" +
            "}";
    }


}
