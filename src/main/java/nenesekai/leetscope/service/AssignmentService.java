package nenesekai.leetscope.service;

import nenesekai.leetscope.entity.Assignment;
import org.springframework.stereotype.Service;
import nenesekai.leetscope.model.Result;

import java.util.List;

@Service
public interface AssignmentService {
    public Result<Assignment> createAssignment(Assignment assignment);

    public Result<Assignment> updateAssignment(Assignment assignment);

    public Result<List<Assignment>> listAllAssignments();
}
