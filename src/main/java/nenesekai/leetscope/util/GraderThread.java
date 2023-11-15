package nenesekai.leetscope.util;

import org.springframework.stereotype.Component;

public class GraderThread extends Thread {
    public String fileName;
    public String inputFile;
    public String outputFile;
    public Boolean isFinished = false;
    public Boolean isPass = false;
    public GraderThread(String fileName, String inputFile, String outputFile) {
        this.fileName = fileName;
        this.inputFile = inputFile;
        this.outputFile = outputFile;
    }
    @Override
    public void run() {

    }
}
