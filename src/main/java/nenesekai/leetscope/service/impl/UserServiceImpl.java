package nenesekai.leetscope.service.impl;

import nenesekai.leetscope.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import nenesekai.leetscope.entity.User;
import nenesekai.leetscope.mapper.UserMapper;
import nenesekai.leetscope.model.Result;
import nenesekai.leetscope.util.JwtUtil;

import java.util.List;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public Boolean isValidUserID(Long uid) {
        return userMapper.selectById(uid) != null;
    }

    @Override
    public Result<Void> register(User user) {
        // Check if there are required things like username or password
        if (user.getName() == null || user.getPassword() == null) {
            return new Result<>("INVALID_REGISTER_REQUEST", "Username and Password are both required!", null);
        }
        // Check if there are duplicated username
        List<User> userList = userMapper.listUserByName(user.getName());
        if (!userList.isEmpty()) {
            return new Result<>("INVALID_REGISTER_REQUEST", "Username already token!", null);
        }
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        userMapper.insert(user);
        return Result.success("Successfully registered user!");
    }

    @Override
    public Result<String> login(User user) {
        if (user.getName() == null || user.getPassword() == null) {
            return new Result<>("INVALID_LOGIN_REQUEST", "Username and Password cannot be blank!", null);
        }
        List<User> userList = userMapper.listUserByName(user.getName());
        if (userList.isEmpty() || !userList.get(0).getPassword().equals(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()))) {
            return new Result<>("INVALID_LOGIN_REQUEST", "Invalid Usernamd or Password", null);
        }
        String token = JwtUtil.generateToken(String.valueOf(userList.get(0).getId()));
        return Result.success("Successfully Login", token);
    }

    @Override
    public Result<User> getUserById(Long uid) {
        User user = userMapper.selectById(uid);
        if (user == null) {
            return new Result<>("USER_NOT_FOUND", "User Not Found", null);
        } else {
            return Result.success(user);
        }
    }
}
