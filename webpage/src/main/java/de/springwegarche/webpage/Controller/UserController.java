package de.springwegarche.webpage.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.springwegarche.webpage.Models.User;
import de.springwegarche.webpage.Repositories.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    
    @Autowired
    private UserRepository repository;

    @GetMapping("/init")
    public ResponseEntity<Object> init() {
        try {
            User admin = new User(0, "admin", "admin");
            createUser(admin);
            return ResponseEntity.ok().body("ok");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Admin exists");
        }
    }
    @GetMapping("/all")
    public List <User> getAllUsers() {
        List<User> list = repository.findAll();

        for (User user : list) {
            user.setPassword("***");
        }

        return list;
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getOneUser(@PathVariable(value = "id") Long id) {
        Optional<User> result;
        result = repository.findById(id);

        if(result.isPresent()) {
            return ResponseEntity.ok().body(result.get());
        } else {
            return ResponseEntity.badRequest().body("no id match");
        }
    }
     
    private User createUser(User user) {
        return repository.save(user);
    }

    @PostMapping("/add")
    public User createEmployee(@Validated @RequestBody User user) {
        return repository.save(user);
    }

    @PutMapping("/change/password/{id}")
    public ResponseEntity <Object> changePassword(@PathVariable(value = "id") Long userId,
        @Validated @RequestBody User userDetails) {

        ResponseEntity<Object> isThereResponse = getOneUser(userId);
        if(isThereResponse.getStatusCode() == HttpStatus.OK) {

            User user = (User)isThereResponse.getBody();
            user.setPassword(userDetails.getPassword());

            final User updatedUser = repository.save(user);
            return ResponseEntity.ok(updatedUser);

        } else {
            return ResponseEntity.badRequest().body("no id match");
        }
    }

    
}