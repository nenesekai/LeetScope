package nenesekai.leetscope.controller;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import nenesekai.leetscope.entity.Assignment;
import nenesekai.leetscope.entity.User;
import nenesekai.leetscope.model.NoDataResult;
import nenesekai.leetscope.service.StorageService;
import nenesekai.leetscope.service.UserService;
import nenesekai.leetscope.util.JwtUtil;
import org.springframework.web.bind.annotation.*;
import nenesekai.leetscope.model.Result;
import nenesekai.leetscope.service.AssignmentService;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.Properties;

@RestController
@RequestMapping("/assignment")
public class AssignmentController {
    @Resource
    AssignmentService assignmentService;
    @Resource
    UserService userService;
    @Resource
    StorageService storageService;

    @PostMapping("/create")
    public Result createAssignment(
            @RequestBody() Assignment assignment,
            @RequestHeader(name = "Authorization") String authorization
    ) {
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            return NoDataResult.failed(Result.INVALID_TOKEN_CODE, "No Token Provided or Invalid Token");
        }
        String token = authorization.replace("Bearer ", "");
        try {
            Integer uid = Integer.valueOf(JwtUtil.parseToken(token));
            User user = userService.getUserById(uid);
            assignment.setCreateTime(new Date());
            assignment.setUid(user.getId());
            return assignmentService.createAssignment(assignment);
        } catch (ExpiredJwtException e) {
            return NoDataResult.failed(Result.EXPIRED_TOKEN_CODE, "Token Expired");
        } catch (Exception e) {
            return NoDataResult.failed(Result.INVALID_TOKEN_CODE, "Token is Invalid");
        }
    }

    @PostMapping("/upload/inputFile")
    public Result uploadInputFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("assignmentId") Integer assignmentId,
            @RequestHeader("Authorization") String authorization
    ) {
        storageService.store("samples", String.valueOf(assignmentId), "input", file);
        return NoDataResult.success();
    }

    @PostMapping("/upload/outputFile")
    public Result uploadOutputFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("assignmentId") Integer assignmentId,
            @RequestHeader("Authorization") String authorization
    ) {
        storageService.store("samples", String.valueOf(assignmentId), "output", file);
        return NoDataResult.success();
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
    public Result deleteAssignment(@RequestParam(name = "id") Integer id) {
        return assignmentService.deleteAssignment(id);
    }

    @GetMapping("/get")
    public Result getAssignment(@RequestParam(name = "id") Integer id) {
        return assignmentService.getAssignmentById(id);
    }
}
