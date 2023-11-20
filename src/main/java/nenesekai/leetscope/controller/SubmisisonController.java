package nenesekai.leetscope.controller;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.annotation.Resource;
import nenesekai.leetscope.entity.Submission;
import nenesekai.leetscope.entity.User;
import nenesekai.leetscope.model.NoDataResult;
import nenesekai.leetscope.model.Result;
import nenesekai.leetscope.service.StorageService;
import nenesekai.leetscope.service.SubmissionService;
import nenesekai.leetscope.service.UserService;
import nenesekai.leetscope.util.JwtUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@RestController
@RequestMapping("/submission")
public class SubmisisonController {
    @Resource
    StorageService storageService;
    @Resource
    SubmissionService submissionService;
    @Resource
    UserService userService;

    @PostMapping("/upload")
    public Result upload(
            @RequestParam("file") MultipartFile file,
            @RequestParam("assignmentId") Long assignmentId,
            @RequestHeader("Authorization") String authorization
    ) {
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            return NoDataResult.failed(Result.INVALID_TOKEN_CODE, "No Token Provided");
        }
        try {
            String token = authorization.replace("Bearer ", "");
            Long uid = Long.valueOf(JwtUtil.parseToken(token));
            User user = userService.getUserById(uid);
            Submission submission = new Submission();
            submission.setUid(user.getId());
            submission.setAssignmentId(assignmentId);
            submission.setFileName(file.getOriginalFilename());
            submission.setCreateTime(new Date());
            submission.setIsGraded(false);
            submission.setIsPass(false);
            submissionService.create(submission);
            storageService.store(String.valueOf(submission.getId()), file);
            return NoDataResult.success();
        } catch (ExpiredJwtException e) {
            return NoDataResult.failed(Result.EXPIRED_TOKEN_CODE, "Token Expired");
        } catch (Exception e) {
            return NoDataResult.failed(Result.INVALID_TOKEN_CODE, "Token is invalid");
        }
    }
}
