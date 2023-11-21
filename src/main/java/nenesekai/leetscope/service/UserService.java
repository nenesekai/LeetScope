package nenesekai.leetscope.service;

import nenesekai.leetscope.util.exception.InvalidUserIdException;
import org.springframework.stereotype.Service;
import nenesekai.leetscope.entity.User;
import nenesekai.leetscope.model.Result;

import java.util.List;

@Service
public interface UserService {
    Result register(User user);

    Result login(User user);

    Boolean isValidUserId(Integer uid);

    Boolean isTeacher(Integer uid);

    Boolean isStudent(Integer uid);

    User getUserById(Integer uid) throws InvalidUserIdException;

    Result listUsersByName(String name);
}
