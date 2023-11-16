package nenesekai.leetscope.service.impl;

import nenesekai.leetscope.entity.TestCase;
import nenesekai.leetscope.mapper.AssignmentMapper;
import nenesekai.leetscope.mapper.TestCaseMapper;
import nenesekai.leetscope.model.DataResult;
import nenesekai.leetscope.model.NoDataResult;
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

    public Result createTestCase(TestCase testCase) {
        if (assignmentMapper.selectById(testCase.getAssignmentId()) == null) {
            return NoDataResult.failed("INVALID_ASSIGNMENT_ID", "Invalid Assignment Id");
        }
        testCaseMapper.insert(testCase);
        return DataResult.success(testCase);
    }
}
