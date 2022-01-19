package de.springwegarche.webpage.Util.Mqtt.Util.Database;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import de.springwegarche.webpage.Util.Mqtt.Models.Interfaces.ITopic;

@Entity
@Table(name = "Topic")
public class Topic extends ITopic{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private long id;
    @Column(updatable = false, unique = false, nullable = false)
    private String topicName;
    @Column(updatable = false, unique = false, nullable = true)
    private long parent_id;
    @Column(updatable = true, unique = false, nullable = true)
    private String info;
    @Column(updatable = true, unique = false, nullable = true)
    private String nameSetByUser;


    public Topic(String topicName, String info) {
        this.topicName = topicName;
        this.info = info;
    }
    public Topic(String topicName, long parent_id) {
        this.topicName = topicName;
        this.parent_id = parent_id;
        //System.out.println(parent_id);
    }
    public Topic(String topicName) {
        this.topicName = topicName;
    }


    public Topic() {
    }

    public Topic(long id, String topicName, long parent_id, String info, String nameSetByUser) {
        this.id = id;
        this.topicName = topicName;
        this.parent_id = parent_id;
        this.info = info;
        this.nameSetByUser = nameSetByUser;
    }
    public Topic(String topicName, long parent_id, String info) {
        this.topicName = topicName;
        this.parent_id = parent_id;
        this.info = info;
    }

    public long getId() {
        return this.id;
    }

    public String getName() {
        return this.topicName;
    }

    public long getParent_id() {
        return this.parent_id;
    }

    public void setParent_id(long parent_id) {
        this.parent_id = parent_id;
    }

    public String getInfo() {
        return this.info;
    }
    
    public void setId(long id) {
        this.id = id;
    }

    public String getTopicName() {
        return this.topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }
    public void setInfo(String info) {
        this.info = info;
    }

    public String getNameSetByUser() {
        return this.nameSetByUser;
    }

    public void setNameSetByUser(String nameSetByUser) {
        this.nameSetByUser = nameSetByUser;
    }




    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Topic)) {
            return false;
        }
        Topic topic = (Topic) o;
        return id == topic.id && Objects.equals(topicName, topic.topicName) && parent_id == topic.parent_id && Objects.equals(info, topic.info);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, topicName, parent_id, info);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", topicName='" + getName() + "'" +
            ", parent_id='" + getParent_id() + "'" +
            ", info='" + getInfo() + "'" +
            "}";
    }

    
    /*
    @Column(updatable = true, unique = false, nullable = true)
    private List<Topic> children;
    */
}