package de.springwegarche.webpage.Models.DAO;

import java.util.Objects;

public class RegisterRequest {

    private String username;
    private String password;
    private String email;
    private String language;

    

    public RegisterRequest() {
    }

    public RegisterRequest(String username, String password, String email, String language) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.language = language;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String language) {
        this.language = language;
    } 

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        if (!(o instanceof RegisterRequest)) {
            return false;
        }
        RegisterRequest registerRequest = (RegisterRequest) o;
        return Objects.equals(username, registerRequest.username) && Objects.equals(password, registerRequest.password);
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
            ", email='" + getEmail() + "'" +
            "}";
    }
}
