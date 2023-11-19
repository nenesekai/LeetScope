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

    Boolean isValidUserId(Long uid);

    Boolean isTeacher(Long uid);

    Boolean isStudent(Long uid);

    User getUserById(Long uid) throws InvalidUserIdException;

    Result listUsersByName(String name);
}
