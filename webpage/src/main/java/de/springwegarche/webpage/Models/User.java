package de.springwegarche.webpage.Models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
//@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "user")
public class User implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private long id;

    @Size(min=6)
    @Column(updatable = true, nullable = false)
    private String password;
    @Column(updatable = false, unique = true, nullable = false)
    private String username;
    @Column(updatable = false, nullable = false)
    private String email;
    @Column(updatable = true, nullable = true)
    private String token;

    public User(long id, String password, String username, String email, String token) {
        this.id = id;
        this.password = password;
        this.username = username;
        this.email = email;
        this.token = token;
    }
    public User(String password, String username, String email) {
        this.id = 0;
        this.password = password;
        this.username = username;
        this.email = email;
    }
    public User() {}

    @Column(name = "email", nullable = false)
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return this.token;
    }
    
    public void setToken(String token) {
        this.token = token;
    }
    
    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    @Column(name = "username", nullable = false, unique = true)
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password", nullable = false)
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
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return id == user.id && Objects.equals(username, user.username) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", username='" + getUsername() + "'" +
            ", password='" + getPassword() + "'" +
            "}";
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }

}
