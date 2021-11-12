package de.springwegarche.webpage.Models.DAO;

import java.util.Objects;

public class PasswordForgetRequest {
    private String username;

    public PasswordForgetRequest() {
    }

    public PasswordForgetRequest(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof PasswordForgetRequest)) {
            return false;
        }
        PasswordForgetRequest passwordForgetRequest = (PasswordForgetRequest) o;
        return Objects.equals(username, passwordForgetRequest.username);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(username);
    }

    @Override
    public String toString() {
        return "{" +
            " username='" + getUsername() + "'" +
            "}";
    }
}
