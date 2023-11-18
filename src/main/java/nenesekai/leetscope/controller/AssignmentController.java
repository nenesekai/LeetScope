package nenesekai.leetscope.controller;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import nenesekai.leetscope.entity.Assignment;
import org.springframework.web.bind.annotation.*;
import nenesekai.leetscope.model.Result;
import nenesekai.leetscope.service.AssignmentService;

@RestController
@RequestMapping("/assignment")
public class AssignmentController {
    @Resource
    AssignmentService assignmentService;

    @PostMapping("/create")
    public Result createAssignment(@RequestBody() Assignment assignment, HttpServletRequest request) {
        assignment.setUid((Long) request.getAttribute("uid"));
        return assignmentService.createAssignment(assignment);
    }

    @GetMapping("/listAll")
    public Result listAllAssignments() {
        return assignmentService.listAllAssignments();
    }

    @PostMapping("/update")
    public Result updateAssignment(@RequestBody() Assignment assignment) {
        return assignmentService.updateAssignment(assignment);
    }

    @DeleteMapping("/delete")
    public Result deleteAssignment(@RequestParam(name = "id") Long id) {
        return assignmentService.deleteAssignment(id);
    }

    @GetMapping("/get")
    public Result getAssignment(@RequestParam(name = "id") Long id) {
        return assignmentService.getAssignmentById(id);
    }
}
