package de.springwegarche.webpage.Models.DAO;

import java.util.Objects;

public class AuthenticationRequest {
    private String username;
    private String password;


    public AuthenticationRequest() {
    }

    public AuthenticationRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof AuthenticationRequest)) {
            return false;
        }
        AuthenticationRequest authenticationRequest = (AuthenticationRequest) o;
        return Objects.equals(username, authenticationRequest.username) && Objects.equals(password, authenticationRequest.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }

    @Override
    public String toString() {
        return "{" +
            " username='" + getUsername() + "'" +
            ", password='" + getPassword() + "'" +
            "}";
    }

}
