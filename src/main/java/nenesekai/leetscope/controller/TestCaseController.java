package nenesekai.leetscope.controller;

import nenesekai.leetscope.entity.TestCase;
import nenesekai.leetscope.model.Result;
import nenesekai.leetscope.service.TestCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/TestCase")
public class TestCaseController {
    @Autowired
    TestCaseService testCaseService;
    @PostMapping("/create")
    public Result<TestCase> createTestCase(@RequestBody() TestCase testCase) {
        return  testCaseService.createTestCase(testCase);
    }
}
