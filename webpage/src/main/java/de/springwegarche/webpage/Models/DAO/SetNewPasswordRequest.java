package de.springwegarche.webpage.Models.DAO;

import java.util.Objects;

public class SetNewPasswordRequest {
    private String username;
    private String newPassword;
    private String code;


    public SetNewPasswordRequest() {
    }

    public SetNewPasswordRequest(String username, String newPassword, String code) {
        this.username = username;
        this.newPassword = newPassword;
        this.code = code;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNewPassword() {
        return this.newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof SetNewPasswordRequest)) {
            return false;
        }
        SetNewPasswordRequest setNewPasswordRequest = (SetNewPasswordRequest) o;
        return Objects.equals(username, setNewPasswordRequest.username) && Objects.equals(newPassword, setNewPasswordRequest.newPassword) && Objects.equals(code, setNewPasswordRequest.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, newPassword, code);
    }

    @Override
    public String toString() {
        return "{" +
            " username='" + getUsername() + "'" +
            ", newPassword='" + getNewPassword() + "'" +
            ", code='" + getCode() + "'" +
            "}";
    }

}