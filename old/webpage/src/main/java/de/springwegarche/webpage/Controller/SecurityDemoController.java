package de.springwegarche.webpage.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityDemoController {
    @GetMapping({"/hello"})
    public String hello() {
        return "Hello";
    }
    @GetMapping({"/api/helloSecure"})
    public String helloSecure() {
        return "Hello sicher";
    }
}
