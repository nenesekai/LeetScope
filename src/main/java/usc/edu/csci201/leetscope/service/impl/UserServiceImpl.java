package usc.edu.csci201.leetscope.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import usc.edu.csci201.leetscope.entity.User;
import usc.edu.csci201.leetscope.mapper.UserMapper;
import usc.edu.csci201.leetscope.model.Result;
import usc.edu.csci201.leetscope.service.UserService;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public Result<User> register(User user) {
        logger.trace("Got Register Request");
        logger.trace("Checking if username and password are both filled");
        // Check if there are required things like username or password
        if (user.getName() == null || user.getPassword() == null) {
            return new Result<>("INVALID_REGISTER_REQUEST", "Username and Password are both required!", null);
        }
        logger.trace("Checking if there is another user with the same username");
        // Check if there are duplicated username
        if (!userMapper.listUserByName(user.getName()).isEmpty()) {
            return new Result<>("INVALID_REGISTER_REQUEST", "Username already token!", null);
        }
        logger.trace("Inserting user");
        userMapper.insert(user);
        logger.trace("Done, returning result");
        return Result.success("Successfully registered user!", user);
    }
}
