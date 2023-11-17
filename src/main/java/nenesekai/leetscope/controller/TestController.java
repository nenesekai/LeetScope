package nenesekai.leetscope.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping("/getHello")
    public String hello(@RequestParam(name = "name", defaultValue = "World", required = false) String name) {
        return "Hello %s!".formatted(name);
    }
}
