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
    public Result register(
            @RequestParam("name") String name,
            @RequestParam("password") String password,
            @RequestParam(value = "isTeacher", required = false) Boolean isTeacher,
            @RequestParam(value = "isStudent", required = false) Boolean isStudent
    ) {
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        user.setIsTeacher(isTeacher != null && isTeacher);
        user.setIsStudent(isStudent != null && isStudent);
        return userService.register(user);
    }

    @PostMapping("/login")
    public Result login(@RequestBody() User user) {
        return userService.login(user);
    }
}
