package utils;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class FailedTestRecorder {
    private static final String FILE_PATH = "failed_tests.txt";

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
        String testName = className + "#" + methodName;
        Set<String> currentFailedTests = getFailedTests();
        if (!currentFailedTests.contains(testName)) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
                bw.write(testName);
                bw.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void clearFailedTests() {
        File file = new File(FILE_PATH);
        if (file.exists() && file.isFile()) {
            file.delete();
        }
    }
}