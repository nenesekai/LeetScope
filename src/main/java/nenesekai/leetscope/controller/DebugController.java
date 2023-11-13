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
    public Result getUser(@RequestParam(name = "id", required = false) Long uid,
                                @RequestParam(name = "name", required = false) String name) {
        if (uid != null) {
            return userService.getUserById(uid);
        } else if (name != null) {
            return userService.listUsersByName(name);
        } else {
            return new Result("INVALID_REQUEST", "Parameters cannot be all blank", null);
        }
    }
}
