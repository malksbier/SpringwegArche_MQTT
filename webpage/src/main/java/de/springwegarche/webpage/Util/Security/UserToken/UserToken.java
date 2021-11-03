package de.springwegarche.webpage.Util.Security.UserToken;

import java.util.Objects;

public class UserToken {
    private String token;

    UserToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }

    void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof UserToken)) {
            return false;
        }
        UserToken userToken = (UserToken) o;
        return Objects.equals(token, userToken.token);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(token);
    }

    @Override
    public String toString() {
        return "{" +
            " token='" + getToken() + "'" +
            "}";
    }


}
