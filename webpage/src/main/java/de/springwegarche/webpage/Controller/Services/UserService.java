package de.springwegarche.webpage.Controller.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import de.springwegarche.webpage.Models.User;
import de.springwegarche.webpage.Models.Repositories.UserRepository;

@Service
public class UserService implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO get User from DB
        return userRepository.findByUsername(username);
    }
    public User getUserByUsername(String username) throws UsernameNotFoundException {
        // TODO get User from DB
        return userRepository.findByUsername(username);
    }
    public int setToken(String token, String username) {
        return userRepository.setUserToken(token, username);
    }
    public int setPassword(String password, String username) {
        return userRepository.setUserPassword(password, username);
    }
    public void deleteUser(User user) {
        userRepository.delete(user);
    } 
    public void addUser(User user) {
        userRepository.saveAndFlush(user);
    }
}
