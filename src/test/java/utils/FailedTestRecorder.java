package utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FailedTestRecorder {
    private static final String FILE_PATH = "failed_tests.bat";

    public static void recordFailedTest(String className, String methodName) {
        String methodNameWithoutParentheses = methodName.replaceAll("\\([^()]*\\)", "");
        String testName = className + "#" + methodNameWithoutParentheses + ",";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            String line = Files.readString(Paths.get(FILE_PATH), StandardCharsets.UTF_8);
            if (line != null) {
                if (!line.contains(className + "#" + methodNameWithoutParentheses)) {
                    bw.write(testName);
                }

            } else {
                bw.write("mvn test -Dtest=" + testName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}




