package nenesekai.leetscope.controller;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.annotation.Resource;
import nenesekai.leetscope.model.NoDataResult;
import nenesekai.leetscope.service.UserService;
import nenesekai.leetscope.util.JwtUtil;
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

    @GetMapping("/whoAmI")
    public Result whoAmI(@RequestHeader(name = "Authorization", required = false) String authorization) {
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            return NoDataResult.failed(Result.INVALID_PARAM_CODE, "No Token!");
        }
        try {
            Long uid = Long.valueOf(JwtUtil.parseToken(authorization.replace("Bearer ", "")));
            System.out.println(uid);
            return userService.getUserById(uid);
        } catch (ExpiredJwtException e) {
            return NoDataResult.failed(Result.INVALID_PARAM_CODE, "Token Expired!");
        }
    }
}
