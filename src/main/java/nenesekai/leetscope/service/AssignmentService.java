package nenesekai.leetscope.service;

import nenesekai.leetscope.entity.Assignment;
import org.springframework.stereotype.Service;
import nenesekai.leetscope.model.Result;

@Service
public interface AssignmentService {
    Result createAssignment(Assignment assignment);

    Result updateAssignment(Assignment assignment);

    Result listAllAssignments();

    Result deleteAssignment(Integer id);

    Result getAssignmentById(Integer id);
}
