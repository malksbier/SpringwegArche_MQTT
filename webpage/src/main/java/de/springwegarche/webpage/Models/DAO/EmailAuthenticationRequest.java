package de.springwegarche.webpage.Models.DAO;

import java.util.Objects;

public class EmailAuthenticationRequest {
    private String code;
    private String username;


    public EmailAuthenticationRequest() {
    }

    public EmailAuthenticationRequest(String code, String username) {
        this.code = code;
        this.username = username;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
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
        if (!(o instanceof EmailAuthenticationRequest)) {
            return false;
        }
        EmailAuthenticationRequest emailAuthenticationRequest = (EmailAuthenticationRequest) o;
        return Objects.equals(code, emailAuthenticationRequest.code) && Objects.equals(username, emailAuthenticationRequest.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, username);
    }

    @Override
    public String toString() {
        return "{" +
            " code='" + getCode() + "'" +
            ", username='" + getUsername() + "'" +
            "}";
    }


}
