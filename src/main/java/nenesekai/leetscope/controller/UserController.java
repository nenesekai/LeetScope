package nenesekai.leetscope.controller;

import jakarta.annotation.Resource;
import nenesekai.leetscope.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import nenesekai.leetscope.entity.User;
import nenesekai.leetscope.model.Result;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    UserService userService;

    @PostMapping("/register")
    public Result register(@RequestBody() User user) {
        return userService.register(user);
    }

    @PostMapping("/login")
    public Result login(@RequestBody() User user) {
        return userService.login(user);
    }
}
