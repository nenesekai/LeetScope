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

import java.io.File;
import java.io.IOException;
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
    Path codeFile;
    Path binaryFile;
    Path inputFile;
    Path outputFile;
    Path userOutputFile;
    Path shellLocation;

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
        this.storageLocation = storageProperties.storageLocation;
        this.compilerLocation = storageProperties.compilerLocation.normalize().toAbsolutePath();
        this.codeFile = this.storageLocation.resolve(
                Paths.get("codes", String.valueOf(submission.getId()), submission.getFileName())
        ).normalize().toAbsolutePath();
        this.binaryFile = this.storageLocation.resolve(
                Paths.get("executables", String.valueOf(submission.getId()), "main")
        ).normalize().toAbsolutePath();
        this.inputFile = this.storageLocation.resolve(
                Paths.get("samples", String.valueOf(submission.getAssignmentId()), "input.txt")
        ).normalize().toAbsolutePath();
        this.outputFile = this.storageLocation.resolve(
                Paths.get("samples", String.valueOf(submission.getAssignmentId()), "output.txt")
        ).normalize().toAbsolutePath();
        this.userOutputFile = this.storageLocation.resolve(
                Paths.get("executables", String.valueOf(submission.getId()), "userOutput.txt")
        ).normalize().toAbsolutePath();
        this.shellLocation = storageProperties.shellLocation;
    }

    public void compile() {
        try {
            Files.createDirectories(binaryFile.getParent());
            String[] commands = {
                    compilerLocation.toString(),
                    codeFile.toString(),
                    "-o",
                    binaryFile.toString()
            };
            for (String command : commands) {
                System.out.println(command);
            }
            Runtime runtime = Runtime.getRuntime();
            Process process = runtime.exec(commands);
            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void execute() {
        try {
            Runtime runtime = Runtime.getRuntime();
            Files.createFile(userOutputFile);
            String[] commands = {
                    shellLocation.toString(),
                    "-c",
                    binaryFile.toString(),
                    "<",
                    inputFile.toString(),
                    ">",
                    userOutputFile.toString()
            };
            Process process = runtime.exec(commands);
            Scanner scanner = new Scanner(process.getInputStream());
            process.waitFor();
            while (scanner.hasNext()) {
                System.out.println(scanner.next());
            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void grade() {
        try {
            Long result = Files.mismatch(userOutputFile, outputFile);
            if (result == -1) {
                submission.setIsPass(true);
            }
            submissionMapper.updateById(submission);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        submission.setIsGraded(true);
        compile();
        execute();
        grade();
    }
}
