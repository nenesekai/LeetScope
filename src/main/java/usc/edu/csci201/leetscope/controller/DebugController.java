package usc.edu.csci201.leetscope.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import usc.edu.csci201.leetscope.entity.User;
import usc.edu.csci201.leetscope.model.Result;
import usc.edu.csci201.leetscope.service.UserService;

@RestController
@RequestMapping("/debug")
public class DebugController {
    @Autowired
    UserService userService;

    @GetMapping("/getUser")
    public Result<User> getUser(@RequestParam(name = "id", required = true) Long uid) {
        return userService.getUserById(uid);
    }
}
