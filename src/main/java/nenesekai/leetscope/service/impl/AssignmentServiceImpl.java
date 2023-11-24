package nenesekai.leetscope.service.impl;

import jakarta.annotation.Resource;
import nenesekai.leetscope.config.StorageProperties;
import nenesekai.leetscope.entity.Assignment;
import nenesekai.leetscope.mapper.SubmissionMapper;
import nenesekai.leetscope.model.DataResult;
import nenesekai.leetscope.model.NoDataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import nenesekai.leetscope.mapper.AssignmentMapper;
import nenesekai.leetscope.model.Result;
import nenesekai.leetscope.service.AssignmentService;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;

@Component
public class AssignmentServiceImpl implements AssignmentService {
    @Resource
    AssignmentMapper assignmentMapper;
    @Resource
    SubmissionMapper submissionMapper;
    @Resource
    StorageProperties storageProperties;

    @Override
    public Result createAssignment(Assignment assignment) {
        // Check title
        if (assignment.getTitle() == null || assignment.getUid() == null || assignment.getAllowedAttempts() == 0 || assignment.getDeadline() == null) {
            return NoDataResult.failed(Result.MISSING_PARAM_CODE, "Required Information Cannot be Blank");
        }
        // Check if deadline is valid
        if (assignment.getDeadline().compareTo(new Date()) < 0) {
            return NoDataResult.failed(Result.INVALID_PARAM_CODE, "Deadline cannot be earlier than now");
        }
        // Check if allowed attempts value is valid
        if (assignment.getAllowedAttempts() < 1) {
            return NoDataResult.failed(Result.INVALID_PARAM_CODE, "Allowed Attempts cannot be less than 1");
        }
        assignment.setCreateTime(new Date());
        assignmentMapper.insert(assignment);
        return DataResult.success(assignment);
    }

    @Override
    public Result updateAssignment(Assignment assignment) {
        if (assignmentMapper.selectById(assignment.getId()) == null) {
            return NoDataResult.failed(Result.INVALID_PARAM_CODE, "Invalid Assignment ID!");
        }
        assignmentMapper.updateById(assignment);
        return DataResult.success(assignmentMapper.selectById(assignment.getId()));
    }

    @Override
    public Result listAllAssignments() {
        return DataResult.success(assignmentMapper.listAllAssignments());
    }

    @Override
    public Result deleteAssignment(Integer id) {
        submissionMapper.deleteSubmissionByAssignmentId(id);
        assignmentMapper.deleteById(id);
        try {
            Files.deleteIfExists(storageProperties.getRoot().resolve("samples").resolve(String.valueOf(id)));
        } catch(IOException e) {
            e.printStackTrace();
        }
        return DataResult.success("Assignment Deleted");
    }

    @Override
    public Result getAssignmentById(Integer id) {
        Assignment assignment = assignmentMapper.selectById(id);
        if (assignment == null) {
            return NoDataResult.failed(Result.INVALID_PARAM_CODE, "Invalid Assignment ID");
        } else {
            return DataResult.success(assignment);
        }
    }
}
