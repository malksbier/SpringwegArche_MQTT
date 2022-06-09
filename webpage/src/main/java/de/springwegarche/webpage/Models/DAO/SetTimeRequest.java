package de.springwegarche.webpage.Models.DAO;

import java.time.LocalTime;
import java.util.Objects;

public class SetTimeRequest {
    private long id;
    private String start;
    private String stop;



    public SetTimeRequest() {
    }

    public SetTimeRequest(long id, String start, String stop) {
        this.id = id;
        this.start = start;
        this.stop = stop;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
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
        if (!(o instanceof SetTimeRequest)) {
            return false;
        }
        SetTimeRequest setTimeRequest = (SetTimeRequest) o;
        return id == setTimeRequest.id && Objects.equals(start, setTimeRequest.start) && Objects.equals(stop, setTimeRequest.stop);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, start, stop);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", start='" + getStart() + "'" +
            ", stop='" + getStop() + "'" +
            "}";
    }
}
