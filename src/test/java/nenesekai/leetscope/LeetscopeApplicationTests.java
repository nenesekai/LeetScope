package nenesekai.leetscope;

import nenesekai.leetscope.entity.Assignment;
import nenesekai.leetscope.entity.User;
import nenesekai.leetscope.mapper.AssignmentMapper;
import nenesekai.leetscope.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.Date;

@SpringBootTest
class LeetscopeApplicationTests {
    @Autowired
    UserMapper userMapper;

    @Autowired
    AssignmentMapper assignmentMapper;

    @Test
    void contextLoads() {
        Assert.isTrue(true, "");
    }

    @Test
    void updateAssignment() {
        System.out.println("Creating a user that created the assignment");
        User user = new User();
        user.setName("Kusanagi Nene");
        user.setPassword("114514");
        user.setIsTeacher(true);
        userMapper.insert(user);
        System.out.println("Creating an assignment");
        Assignment assignment = new Assignment();
        assignment.setUid(user.getId());
        assignment.setTitle("Hello World Assignment");
        assignment.setCreateTime(new Date());
        assignment.setDeadline(new Date());
        assignment.setAllowedAttempts(2);
        assignmentMapper.insert(assignment);
        System.out.println("Check to see if we can find that assignment");
        Assert.isTrue(assignmentMapper.selectById(assignment.getId()) != null, "");
        System.out.println("Trying to update that assignment from allowed attempts 2 to 1");
        Assignment anotherAssignment = new Assignment();
        anotherAssignment.setId(assignment.getId());
        anotherAssignment.setAllowedAttempts(1);
        assignmentMapper.updateById(anotherAssignment);
        Assert.isTrue(assignmentMapper.selectById(assignment.getId()).getAllowedAttempts() == 1, "");
    }

}
