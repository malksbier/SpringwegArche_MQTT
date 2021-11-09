package de.springwegarche.webpage.Util.Security.UserToken;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "user_token")
public class UserToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "token", nullable = false)
    private String token;
    
    private long userId;


    public UserToken(String token, long userId) {
        this.id = 0;
        this.token = token;
        this.userId = userId;
    }

    public UserToken(long id, String token, long userId) {
        this.id = id;
        this.token = token;
        this.userId = userId;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getUserId() {
        return this.userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof UserToken)) {
            return false;
        }
        UserToken userToken = (UserToken) o;
        return id == userToken.id && Objects.equals(token, userToken.token) && userId == userToken.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, token, userId);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", token='" + getToken() + "'" +
            ", userId='" + getUserId() + "'" +
            "}";
    }

}
