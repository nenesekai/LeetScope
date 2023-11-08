package usc.edu.csci201.leetscope.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import usc.edu.csci201.leetscope.entity.User;
import usc.edu.csci201.leetscope.model.Result;
import usc.edu.csci201.leetscope.service.UserService;
import usc.edu.csci201.leetscope.service.impl.UserServiceImpl;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public Result<User> register(@RequestBody() User user) {
        return userService.register(user);
    }
}
