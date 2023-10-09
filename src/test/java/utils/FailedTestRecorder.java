package utils;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class FailedTestRecorder {
    private static final String FILE_PATH = "failed_tests.bat";

    public static Set<String> getFailedTests() {
        Set<String> failedTests = new HashSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                failedTests.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return failedTests;
    }

    public static void recordFailedTest(String className, String methodName) {
        String methodNameWithoutParentheses = methodName.replaceAll("\\([^()]*\\)", "");
        String testName = className + "#" + methodNameWithoutParentheses + ",";
        Set<String> currentFailedTests = getFailedTests();
        if (!currentFailedTests.contains(testName)) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
                BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
                String line = br.readLine();
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

}

