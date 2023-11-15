package nenesekai.leetscope.service.impl;

import nenesekai.leetscope.entity.TestCase;
import nenesekai.leetscope.mapper.AssignmentMapper;
import nenesekai.leetscope.mapper.TestCaseMapper;
import nenesekai.leetscope.model.Result;
import nenesekai.leetscope.service.AssignmentService;
import nenesekai.leetscope.service.TestCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestCaseServiceImpl implements TestCaseService {
    @Autowired
    AssignmentMapper assignmentMapper;
    @Autowired
    TestCaseMapper testCaseMapper;

    public Result<TestCase> createTestCase(TestCase testCase) {
        if (assignmentMapper.selectById(testCase.getAssignmentId()) == null) {
            return new Result("INVALID_ASSIGNMENT_ID", "Invalid Assignment Id", null);
        }
        testCaseMapper.insert(testCase);
        return Result.success(testCase);
    }
}
