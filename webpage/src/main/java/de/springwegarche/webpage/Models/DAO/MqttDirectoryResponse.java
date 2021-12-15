package de.springwegarche.webpage.Models.DAO;

import java.util.ArrayList;
import java.util.Objects;

import de.springwegarche.webpage.Util.Mqtt.Models.DAO.InvertedTopic;

public class MqttDirectoryResponse {
    private ArrayList<InvertedTopic> topics;

    public MqttDirectoryResponse(ArrayList<InvertedTopic> topics) {
        this.topics = topics;
    }

    public ArrayList<InvertedTopic> getTopics() {
        return this.topics;
    }

    public void setTopics(ArrayList<InvertedTopic> topics) {
        this.topics = topics;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof MqttDirectoryResponse)) {
            return false;
        }
        MqttDirectoryResponse mqttDirectoryResponse = (MqttDirectoryResponse) o;
        return Objects.equals(topics, mqttDirectoryResponse.topics);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(topics);
    }

    @Override
    public String toString() {
        return "{" +
            " topics='" + getTopics() + "'" +
            "}";
    }

}