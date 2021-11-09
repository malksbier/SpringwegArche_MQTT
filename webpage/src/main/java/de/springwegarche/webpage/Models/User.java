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
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import de.springwegarche.webpage.Util.Security.UserToken.UserToken;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String password;
    private String username;
    private String email;

    @OneToMany(mappedBy = "id")
    private List<UserToken> tokens = new ArrayList<UserToken>();


    

    public List<UserToken> getTokens() {
        return this.tokens;
    }
    public void addToken(UserToken token) {
        this.tokens.add(token);
    }

    public void setTokens(List<UserToken> tokens) {
        this.tokens = tokens;
    }

    public User(long id, String password, String username, String email, List<UserToken> tokens) {
        this.id = id;
        this.password = password;
        this.username = username;
        this.email = email;
        this.tokens = tokens;
    }
    public User(long id, String password, String username, String email) {
        this.id = id;
        this.password = password;
        this.username = username;
        this.email = email;
    }
    public User(String password, String username, String email) {
        this.id = 0;
        this.password = password;
        this.username = username;
        this.email = email;
    }
    public User(long id, String password, String username) {
        this.id = id;
        this.password = password;
        this.username = username;
        this.email = "";
    }
    public User(String password, String username) {
        this.id = 0;
        this.password = password;
        this.username = username;
        this.email = "";
    }

    @Column(name = "email", nullable = false)
    public String getEmail() {
        return this.email;
    }

    
    public void setEmail(String email) {
        this.email = email;
    }

    public User() {
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

    public User username(String username) {
        setUsername(username);
        return this;
    }

    public User password(String password) {
        setPassword(password);
        return this;
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
