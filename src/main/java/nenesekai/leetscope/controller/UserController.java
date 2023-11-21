package nenesekai.leetscope.controller;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.annotation.Resource;
import nenesekai.leetscope.model.DataResult;
import nenesekai.leetscope.model.NoDataResult;
import nenesekai.leetscope.service.UserService;
import nenesekai.leetscope.util.JwtUtil;
import nenesekai.leetscope.util.exception.InvalidUserIdException;
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

    @GetMapping("/getCurrent")
    public Result getCurrentUser(@RequestHeader(name = "Authorization", required = false) String authorization) {
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            return NoDataResult.failed(Result.INVALID_TOKEN_CODE, "No Token Provided!");
        }
        String token = authorization.replace("Bearer ", "");
        try {
            Integer uid = Integer.valueOf(JwtUtil.parseToken(token));
            User user = userService.getUserById(uid);
            return DataResult.success(user);
        } catch (ExpiredJwtException e) {
            return NoDataResult.failed(Result.EXPIRED_TOKEN_CODE, e.getMessage());
        } catch (Exception e) {
            return NoDataResult.failed(Result.INVALID_TOKEN_CODE, e.getMessage());
        }
    }

    @GetMapping("/get")
    public Result getUser(@RequestParam(name = "id") Integer uid) {
        try {
            User user = userService.getUserById(uid);
            return DataResult.success(user);
        } catch (InvalidUserIdException e) {
            return NoDataResult.failed(Result.INVALID_PARAM_CODE, e.getMessage());
        }
    }
}
