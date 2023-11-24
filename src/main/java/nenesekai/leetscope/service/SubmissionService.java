package nenesekai.leetscope.service;

import nenesekai.leetscope.entity.Submission;
import nenesekai.leetscope.model.Result;
import org.springframework.stereotype.Service;

@Service
public interface SubmissionService {
    public Result create(Submission submission);

    public Result listSubmissionsByUidAndAssignmentId(Integer uid, Integer assignmentId);
    
    public Result listSubmissionByAssignmentId(Integer assignmentId);
}
