package de.springwegarche.webpage.Util.Security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import de.springwegarche.webpage.Models.User;

public class UserDetailsImplement implements UserDetails {

    private User user;

    public UserDetailsImplement(User user) {
        super();
        this.user = user;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("User"));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO not used
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO not used
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO not used
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO not used
        return true;
    }
    
}
