package nenesekai.leetscope.service;

import org.springframework.stereotype.Service;
import nenesekai.leetscope.entity.User;
import nenesekai.leetscope.model.Result;

import java.util.List;

@Service
public interface UserService {
    public Result<Void> register(User user);

    public Result<String> login(User user);

    public Boolean isValidUserId(Long uid);

    public Boolean isStudent(Long uid);

    public Boolean isTeacher(Long uid);

    public Result<User> getUserById(Long uid);

    public Result<List<User>> listUsersByName(String name);
}
