package nenesekai.leetscope.service.impl;

import nenesekai.leetscope.model.DataResult;
import nenesekai.leetscope.model.LoginResult;
import nenesekai.leetscope.model.NoDataResult;
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
    public Boolean isValidUserId(Long uid) {
        return userMapper.selectById(uid) != null;
    }

    @Override
    public Result register(User user) {
        // Check if there are required things like username or password
        if (user.getName() == null || user.getPassword() == null) {
            return NoDataResult.failed("INVALID_REGISTER_REQUEST", "Username and Password are both required!");
        }
        // Check if there are duplicated username
        List<User> userList = userMapper.listUserByName(user.getName());
        if (!userList.isEmpty()) {
            return NoDataResult.failed("INVALID_REGISTER_REQUEST", "Username already token!");
        }
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        userMapper.insert(user);
        return NoDataResult.success("Successfully registered user!");
    }

    @Override
    public Result login(User user) {
        if (user.getName() == null || user.getPassword() == null) {
            return NoDataResult.failed("INVALID_LOGIN_REQUEST", "Username and Password cannot be blank!");
        }
        List<User> userList = userMapper.listUserByName(user.getName());
        if (userList.isEmpty() || !userList.get(0).getPassword().equals(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()))) {
            return NoDataResult.failed("INVALID_LOGIN_REQUEST", "Invalid Usernamd or Password");
        }
        String token = JwtUtil.generateToken(String.valueOf(userList.get(0).getId()));
        return LoginResult.success(token);
    }

    @Override
    public Result getUserById(Long uid) {
        User user = userMapper.selectById(uid);
        if (user == null) {
            return NoDataResult.failed("USER_NOT_FOUND", "User Not Found");
        } else {
            return DataResult.success(user);
        }
    }

    @Override
    public Result listUsersByName(String name) {
        List<User> userList = userMapper.listUserByName(name);
        if (userList.isEmpty()) {
            return NoDataResult.failed("USER_NOT_FOUND", "User Not Found!");
        } else {
            return DataResult.success(userList);
        }
    }

    @Override
    public Boolean isStudent(Long uid) {
        User user = userMapper.selectById(uid);
        return user != null && user.getIsStudent();
    }

    @Override
    public Boolean isTeacher(Long uid) {
        User user = userMapper.selectById(uid);
        return user != null && user.getIsTeacher();
    }
}
