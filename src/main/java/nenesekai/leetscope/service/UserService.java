package nenesekai.leetscope.service;

import org.springframework.stereotype.Service;
import nenesekai.leetscope.entity.User;
import nenesekai.leetscope.model.Result;

@Service
public interface UserService {
    public Result<Void> register(User user);

    public Result<String> login(User user);

    public Boolean isValidUserID(Long uid);

    public Result<User> getUserById(Long uid);
}
