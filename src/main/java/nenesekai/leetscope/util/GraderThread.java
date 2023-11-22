package nenesekai.leetscope.util;

import jakarta.annotation.Resource;
import lombok.Setter;
import nenesekai.leetscope.config.StorageProperties;
import nenesekai.leetscope.entity.Assignment;
import nenesekai.leetscope.entity.Submission;
import nenesekai.leetscope.mapper.AssignmentMapper;
import nenesekai.leetscope.mapper.SubmissionMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GraderThread extends Thread {
    AssignmentMapper assignmentMapper;
    SubmissionMapper submissionMapper;
    Path storageLocation;
    Path compilerLocation;

    Assignment assignment;
    Submission submission;

    public GraderThread(
            Assignment assignment,
            Submission submission,
            AssignmentMapper assignmentMapper,
            SubmissionMapper submissionMapper,
            StorageProperties storageProperties
    ) {
        this.assignment = assignment;
        this.submission = submission;
        this.assignmentMapper = assignmentMapper;
        this.submissionMapper = submissionMapper;
        this.storageLocation = Paths.get(storageProperties.getLocation());
        this.compilerLocation = Paths.get(storageProperties.getCompilerLocation());
    }

    @Override
    public void run() {
        try {
            List<String> commands = new ArrayList<>();
            Path codeFile = this.storageLocation.resolve(
                    Paths.get("codes", String.valueOf(submission.getId()), submission.getFileName())
                    ).normalize().toAbsolutePath();
            Path binaryFile = this.storageLocation.resolve(
                    Paths.get("executables", String.valueOf(submission.getId()), "main")
            ).normalize().toAbsolutePath();
            Files.createDirectories(binaryFile.getParent());
            commands.add("\"%s\" \"%s\" -o \"%s\"".formatted(compilerLocation.toString(), codeFile.toString(), binaryFile.toString()));
            for (String command : commands ) System.out.println(command); // TODO: DELETE
            ProcessBuilder processBuilder = new ProcessBuilder(commands);
            Process process = processBuilder.start();
            Scanner scanner = new Scanner(process.getInputStream());
            if (scanner.hasNext()) {
                System.out.println(scanner.next());
            }
            scanner.close();
            submission.setIsGraded(true);
            submission.setIsPass(true);
            submissionMapper.updateById(submission);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
