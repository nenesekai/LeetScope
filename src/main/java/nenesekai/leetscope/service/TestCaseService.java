package nenesekai.leetscope.service;

import nenesekai.leetscope.entity.TestCase;
import nenesekai.leetscope.model.Result;
import org.springframework.stereotype.Service;

@Service
public interface TestCaseService {
    public Result<TestCase> createTestCase(TestCase testCase);
}
