package usc.edu.csci201.leetscope.service;

import org.springframework.stereotype.Service;
import usc.edu.csci201.leetscope.entity.User;
import usc.edu.csci201.leetscope.model.Result;

@Service
public interface UserService {
    public Result<Void> register(User user);

    public Result<String> login(User user);

    public Boolean isValidUserID(Long uid);

    public Result<User> getUserById(Long uid);
}
