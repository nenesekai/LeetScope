package nenesekai.leetscope.service.impl;

import nenesekai.leetscope.entity.Assignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import nenesekai.leetscope.mapper.AssignmentMapper;
import nenesekai.leetscope.model.Result;
import nenesekai.leetscope.service.AssignmentService;

import java.util.Date;
import java.util.List;

@Component
public class AssignmentServiceImpl implements AssignmentService {
    @Autowired
    AssignmentMapper assignmentMapper;

    @Override
    public Result<Assignment> createAssignment(Assignment assignment) {
        // Check title
        if (assignment.getTitle() == null || assignment.getUid() == null || assignment.getAllowedAttempts() == 0 || assignment.getDeadline() == null) {
            return new Result<>("INVALID_ASSIGNMENT", "Required Information Cannot be Blank", null);
        }
        // Check if deadline is valid
        if (assignment.getDeadline().compareTo(new Date()) < 0) {
            return new Result<>("INVALID_ASSIGNMENT", "Deadline cannot be earlier than now", null);
        }
        // Check if allowed attempts value is valid
        System.out.println(assignment.getAllowedAttempts());
        if (assignment.getAllowedAttempts() < 1) {
            return new Result<>("INVALID_ASSIGNMENT", "Allowed Attempts cannot be less than 1", null);
        }
        assignment.setCreateTime(new Date());
        assignmentMapper.insert(assignment);
        return Result.success(assignment);
    }

    @Override
    public Result<Assignment> updateAssignment(Assignment assignment) {
        if (assignmentMapper.selectById(assignment.getId()) == null) {
            return new Result("INVALID_ASSIGNMENT_ID", "Invalid Assignment ID!", null);
        }
        assignmentMapper.updateById(assignment);
        return Result.success(assignmentMapper.selectById(assignment.getId()));
    }

    @Override
    public Result<List<Assignment>> listAllAssignments() {
        return Result.success(assignmentMapper.listAllAssignments());
    }

    @Override
    public Result<Void> deleteAssignment(Long id) {
        assignmentMapper.deleteById(id);
        return Result.success("Assignment Deleted");
    }
}
