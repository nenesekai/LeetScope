package nenesekai.leetscope.service.impl;

import jakarta.annotation.Resource;
import nenesekai.leetscope.entity.Submission;
import nenesekai.leetscope.mapper.SubmissionMapper;
import nenesekai.leetscope.model.DataResult;
import nenesekai.leetscope.model.NoDataResult;
import nenesekai.leetscope.model.Result;
import nenesekai.leetscope.service.SubmissionService;
import nenesekai.leetscope.util.GradeUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubmissionServiceImpl implements SubmissionService {
    @Resource
    SubmissionMapper submissionMapper;
    @Resource
    GradeUtil gradeUtil;

    @Override
    public Result create(Submission submission) {
        submissionMapper.insert(submission);
        gradeUtil.gradeNewAssignment(submission.getId());
        return DataResult.success(submission);
    }

    @Override
    public Result listSubmissions(Integer uid, Integer assignmentId) {
        return DataResult.success(submissionMapper.listSubmissions(uid, assignmentId));
    }
}
