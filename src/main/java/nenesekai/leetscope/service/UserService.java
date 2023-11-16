package nenesekai.leetscope.service;

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

    Result getUserById(Long uid);

    Result listUsersByName(String name);
}
