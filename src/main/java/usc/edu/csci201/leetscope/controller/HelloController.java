package usc.edu.csci201.leetscope.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {
    @GetMapping("/sayHello")
    public String sayHello(@RequestParam(name = "name", defaultValue = "world") String name) {
        return "Hello %s".formatted(name);
    }
}
