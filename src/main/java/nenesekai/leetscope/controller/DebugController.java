package nenesekai.leetscope.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import nenesekai.leetscope.entity.User;
import nenesekai.leetscope.model.Result;
import nenesekai.leetscope.service.UserService;

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
