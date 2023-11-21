package nenesekai.leetscope.service.impl;

import nenesekai.leetscope.model.DataResult;
import nenesekai.leetscope.model.LoginResult;
import nenesekai.leetscope.model.NoDataResult;
import nenesekai.leetscope.service.UserService;
import nenesekai.leetscope.util.exception.InvalidUserIdException;
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
    public Boolean isValidUserId(Integer uid) {
        return userMapper.selectById(uid) != null;
    }

    @Override
    public Result register(User user) {
        // Check if there are required things like username or password
        if (user.getName() == null || user.getPassword() == null) {
            return NoDataResult.failed(Result.MISSING_PARAM_CODE, "Username and Password are both required!");
        }
        // Check if there are duplicated username
        List<User> userList = userMapper.listUserByName(user.getName());
        if (!userList.isEmpty()) {
            return NoDataResult.failed(Result.INVALID_PARAM_CODE, "Username already token!");
        }
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        userMapper.insert(user);
        return NoDataResult.success("Successfully registered user!");
    }

    @Override
    public Result login(User user) {
        if (user.getName() == null || user.getPassword() == null) {
            return NoDataResult.failed(Result.MISSING_PARAM_CODE, "Username and Password cannot be blank!");
        }
        List<User> userList = userMapper.listUserByName(user.getName());
        if (userList.isEmpty() || !userList.get(0).getPassword().equals(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()))) {
            return NoDataResult.failed(Result.INVALID_PARAM_CODE, "Invalid Username or Password");
        }
        String token = JwtUtil.generateToken(String.valueOf(userList.get(0).getId()));
        return LoginResult.success(token);
    }

    @Override
    public User getUserById(Integer uid) throws InvalidUserIdException {
        User user = userMapper.selectById(uid);
        if (user == null) {
            throw new InvalidUserIdException();
        } else {
            return user;
        }
    }

    @Override
    public Result listUsersByName(String name) {
        List<User> userList = userMapper.listUserByName(name);
        if (userList.isEmpty()) {
            return NoDataResult.failed(Result.INVALID_PARAM_CODE, "User Not Found!");
        } else {
            return DataResult.success(userList);
        }
    }

    @Override
    public Boolean isStudent(Integer uid) {
        User user = userMapper.selectById(uid);
        return user != null && user.getIsStudent();
    }

    @Override
    public Boolean isTeacher(Integer uid) {
        User user = userMapper.selectById(uid);
        return user != null && user.getIsTeacher();
    }
}
