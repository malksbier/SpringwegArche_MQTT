package de.springwegarche.webpage.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityDemoController {
    @RequestMapping({"/hello"})
    public String hello() {
        return "Hello";
    }
    @RequestMapping({"/helloSecure"})
    public String helloSecure() {
        return "Hello sicher";
    }
}
