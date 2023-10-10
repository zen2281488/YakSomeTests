package utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FailedTestRecorder {
    private static final String FILE_PATH = "failed_tests.bat";

    public static void recordFailedTest(String className, String methodName) throws IOException {
        String methodNameWithoutParentheses = methodName.replaceAll("\\([^()]*\\)", "");
        String testName = className + "#" + methodNameWithoutParentheses + ",";
        String line = Files.readString(Paths.get(FILE_PATH), StandardCharsets.UTF_8);
        if (!line.contains(className + "#" + methodNameWithoutParentheses) & line != null) {
            Files.writeString(Paths.get(FILE_PATH), testName, StandardCharsets.UTF_8);
        } else {
            Files.writeString(Paths.get(FILE_PATH), "mvn test -Dtest=" + testName, StandardCharsets.UTF_8);
        }
    }
}





