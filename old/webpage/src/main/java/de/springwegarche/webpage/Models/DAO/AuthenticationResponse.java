package de.springwegarche.webpage.Models.DAO;

import java.util.Objects;

public class AuthenticationResponse {
    private final String jwtToken;

    public AuthenticationResponse(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getJwtToken() {
        return this.jwtToken;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof AuthenticationResponse)) {
            return false;
        }
        AuthenticationResponse authenticationResponse = (AuthenticationResponse) o;
        return Objects.equals(jwtToken, authenticationResponse.jwtToken);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(jwtToken);
    }

    @Override
    public String toString() {
        return "{" +
            " jwtToken='" + getJwtToken() + "'" +
            "}";
    }

}
