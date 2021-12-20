package de.springwegarche.webpage.Util.Mqtt.Models.DAO;

import java.util.ArrayList;
import java.util.Objects;

import de.springwegarche.webpage.Util.Mqtt.Models.Interfaces.ITopic;
import de.springwegarche.webpage.Util.Mqtt.Util.Database.Topic;

public class InvertedTopic extends ITopic{
    private long id;
    private String topicName;
    private String info;
    private ArrayList<InvertedTopic> children;
    private String nameSetByUser;


    public InvertedTopic() {
    }

    public InvertedTopic(long id, String topicName, String info, ArrayList<InvertedTopic> children, String nameSetByUser) {
        this.id = id;
        this.topicName = topicName;
        this.info = info;
        this.children = children;
        this.nameSetByUser = nameSetByUser;
    }
    
    
    public InvertedTopic(Topic topic) {
        this.id = topic.getId();
        this.topicName = topic.getName();
        this.info = topic.getInfo();
        this.children = new ArrayList<InvertedTopic>();
        this.nameSetByUser = topic.getNameSetByUser();
    }


    public long getId() {
        return this.id;
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

    public String getInfo() {
        return this.info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public ArrayList<InvertedTopic> getChildren() {
        return this.children;
    }
    public void addChildren(InvertedTopic child) {
        this.children.add(child);
    }
    public void setChildren(ArrayList<InvertedTopic> children) {
        this.children = children;
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
        if (!(o instanceof InvertedTopic)) {
            return false;
        }
        InvertedTopic invertedTopic = (InvertedTopic) o;
        return id == invertedTopic.id && Objects.equals(topicName, invertedTopic.topicName) && Objects.equals(info, invertedTopic.info) && Objects.equals(children, invertedTopic.children);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, topicName, info, children);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", topicName='" + getTopicName() + "'" +
            ", info='" + getInfo() + "'" +
            ", children='" + getChildren() + "'" +
            "}";
    }

}
