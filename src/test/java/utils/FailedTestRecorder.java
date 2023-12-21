package utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FailedTestRecorder {
    static Path path = Paths.get(ConfProperties.getProperty("failedTestsBatName"));

    public static void recordFailedTest(String className, String methodName) throws IOException {
        String methodNameWithoutParentheses = methodName.replaceAll("\\([^()]*\\)", "");
        String testName = className + "#" + methodNameWithoutParentheses + ",";
        Files.writeString(path, testName + System.lineSeparator(), StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

    public static void newFailedTestFile() throws IOException {
        Files.write(path, "mvn test -Dtest=".getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }
}