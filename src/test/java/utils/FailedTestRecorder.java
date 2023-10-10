package utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FailedTestRecorder {

    public static void recordFailedTest(String className, String methodName) throws IOException {
        String methodNameWithoutParentheses = methodName.replaceAll("\\([^()]*\\)", "");
        String testName = className + "#" + methodNameWithoutParentheses + ",";
        Path path = Paths.get(ConfProperties.getProperty("failedTestsBatName"));
        String line = Files.readString(path, StandardCharsets.UTF_8);
        if (line != null && !line.contains(className + "#" + methodNameWithoutParentheses)) {
            Files.writeString(path, testName, StandardCharsets.UTF_8);
        } else {
            Files.writeString(path, "mvn test -Dtest=" + testName, StandardCharsets.UTF_8);
        }
    }
}





