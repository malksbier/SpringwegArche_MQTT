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
    private String start;
    private String stop;
    
    public InvertedTopic(Topic topic) {
        this.id = topic.getId();
        this.topicName = topic.getName();
        this.info = topic.getInfo();
        this.children = new ArrayList<InvertedTopic>();
        this.nameSetByUser = topic.getNameSetByUser();
        this.start = topic.getStart();
        this.stop = topic.getStop();
    }

    public void addChildren(InvertedTopic invertedTopic) {
        this.children.add(invertedTopic);
    }


    public InvertedTopic() {
    }

    public InvertedTopic(long id, String topicName, String info, ArrayList<InvertedTopic> children, String nameSetByUser, String start, String stop) {
        this.id = id;
        this.topicName = topicName;
        this.info = info;
        this.children = children;
        this.nameSetByUser = nameSetByUser;
        this.start = start;
        this.stop = stop;
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

    public void setChildren(ArrayList<InvertedTopic> children) {
        this.children = children;
    }

    public String getNameSetByUser() {
        return this.nameSetByUser;
    }

    public void setNameSetByUser(String nameSetByUser) {
        this.nameSetByUser = nameSetByUser;
    }

    public String getStart() {
        return this.start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getStop() {
        return this.stop;
    }

    public void setStop(String stop) {
        this.stop = stop;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof InvertedTopic)) {
            return false;
        }
        InvertedTopic invertedTopic = (InvertedTopic) o;
        return id == invertedTopic.id && Objects.equals(topicName, invertedTopic.topicName) && Objects.equals(info, invertedTopic.info) && Objects.equals(children, invertedTopic.children) && Objects.equals(nameSetByUser, invertedTopic.nameSetByUser) && Objects.equals(start, invertedTopic.start) && Objects.equals(stop, invertedTopic.stop);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, topicName, info, children, nameSetByUser, start, stop);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", topicName='" + getTopicName() + "'" +
            ", info='" + getInfo() + "'" +
            ", children='" + getChildren() + "'" +
            ", nameSetByUser='" + getNameSetByUser() + "'" +
            ", start='" + getStart() + "'" +
            ", stop='" + getStop() + "'" +
            "}";
    }


   

    
  



}
