package nenesekai.leetscope.controller;

import jakarta.servlet.http.HttpServletRequest;
import nenesekai.leetscope.entity.Assignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import nenesekai.leetscope.model.Result;
import nenesekai.leetscope.service.AssignmentService;

import java.util.List;

@RestController
@RequestMapping("/assignment")
public class AssignmentController {
    @Autowired
    AssignmentService assignmentService;

    @PostMapping("/create")
    public Result<Assignment> createAssignment(@RequestBody() Assignment assignment, HttpServletRequest request) {
        assignment.setUid((Long) request.getAttribute("uid"));
        return assignmentService.createAssignment(assignment);
    }

    @GetMapping("/listAll")
    public Result<List<Assignment>> listAllAssignments() {
        return assignmentService.listAllAssignments();
    }

    @PostMapping("/update")
    public Result<Assignment> updateAssignment(@RequestBody() Assignment assignment) {
        return assignmentService.updateAssignment(assignment);
    }

    @DeleteMapping("/delete")
    public Result<Void> deleteAssignment(@RequestParam(name = "id") Long id) {
        return assignmentService.deleteAssignment(id);
    }
}
