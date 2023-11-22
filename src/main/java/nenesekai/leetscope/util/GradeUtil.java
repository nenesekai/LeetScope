package nenesekai.leetscope.util;

import jakarta.annotation.Resource;
import nenesekai.leetscope.config.StorageProperties;
import nenesekai.leetscope.entity.Assignment;
import nenesekai.leetscope.entity.Submission;
import nenesekai.leetscope.mapper.AssignmentMapper;
import nenesekai.leetscope.mapper.SubmissionMapper;
import nenesekai.leetscope.service.AssignmentService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GradeUtil {
    @Resource
    AssignmentMapper assignmentMapper;
    @Resource
    SubmissionMapper submissionMapper;
    @Resource
    StorageProperties storageProperties;

    List<GraderThread> graderThreads = new ArrayList<>();

    public void gradeNewAssignment(int submissionId) {
        Submission submission = submissionMapper.selectById(submissionId);
        Assignment assignment = assignmentMapper.selectById(submission.getAssignmentId());
        GraderThread thread = new GraderThread(assignment, submission, assignmentMapper, submissionMapper, storageProperties);
        graderThreads.add(thread);
        thread.start();
    }
}
