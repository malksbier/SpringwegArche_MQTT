package de.springwegarche.webpage.Util.Mqtt.Models.DAO;

import java.util.Objects;

public class NameSetByUserRequest {
    private long id;
    private String NameSetByUser;


    public NameSetByUserRequest() {
    }

    public NameSetByUserRequest(long id, String NameSetByUser) {
        this.id = id;
        this.NameSetByUser = NameSetByUser;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameSetByUser() {
        return this.NameSetByUser;
    }

    public void setNameSetByUser(String NameSetByUser) {
        this.NameSetByUser = NameSetByUser;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof NameSetByUserRequest)) {
            return false;
        }
        NameSetByUserRequest nameSetByUserRequest = (NameSetByUserRequest) o;
        return id == nameSetByUserRequest.id && Objects.equals(NameSetByUser, nameSetByUserRequest.NameSetByUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, NameSetByUser);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", NameSetByUser='" + getNameSetByUser() + "'" +
            "}";
    }

}
