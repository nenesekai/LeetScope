package nenesekai.leetscope.controller;

import nenesekai.leetscope.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import nenesekai.leetscope.entity.User;
import nenesekai.leetscope.model.Result;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/registerLegacy")
    public Result<Void> registerLegacy(@RequestBody() User user) {
        return userService.register(user);
    }

    @PostMapping("/register")
    public Result<Void> register(
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

    @PostMapping("/loginLegacy")
    public Result<String> loginLegacy(@RequestBody() User user) {
        return userService.login(user);
    }

    @PostMapping("/login")
    public Result<String> login(
            @RequestParam("name") String name,
            @RequestParam("password") String password
    ) {
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        return userService.login(user);
    }
}
