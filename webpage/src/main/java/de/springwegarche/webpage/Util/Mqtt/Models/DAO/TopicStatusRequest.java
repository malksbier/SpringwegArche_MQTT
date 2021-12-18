package de.springwegarche.webpage.Util.Mqtt.Models.DAO;

import java.util.Objects;

public class TopicStatusRequest {
    private long id;
    private String status;


    public TopicStatusRequest() {
    }

    public TopicStatusRequest(long id, String status) {
        this.id = id;
        this.status = status;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof TopicStatusRequest)) {
            return false;
        }
        TopicStatusRequest topicStatusRequest = (TopicStatusRequest) o;
        return id == topicStatusRequest.id && Objects.equals(status, topicStatusRequest.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }

}
