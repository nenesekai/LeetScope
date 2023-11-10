package nenesekai.leetscope.controller;

import nenesekai.leetscope.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import nenesekai.leetscope.entity.User;
import nenesekai.leetscope.model.Result;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public Result<Void> register(@RequestBody() User user) {
        return userService.register(user);
    }

    @PostMapping("/login")
    public Result<String> login(@RequestBody() User user) {
        return userService.login(user);
    }
}
