package de.springwegarche.webpage.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import de.springwegarche.webpage.Models.User;
import de.springwegarche.webpage.Repositories.UserRepository;
import de.springwegarche.webpage.Util.Security.UserDetailsImplement;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException(username + " not found");
        }
            
        
        return new UserDetailsImplement(user);
    }
    
}
