package UITests;

import io.qameta.allure.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.engine.discovery.DiscoverySelectors;
import org.junit.platform.engine.discovery.MethodSelector;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import utils.FailedTestRecorder;

import java.io.PrintWriter;
import java.util.Set;

import static utils.FailedTestRecorder.clearFailedTests;

public class RerunFailedTests extends BaseTest {

    @AfterAll
    public static void afterAllRerunedTests() {
        clearFailedTests();
    }

    private static MethodSelector[] selectFailedTests(Set<String> failedTests) {
        return failedTests.stream()
                .map(DiscoverySelectors::selectMethod)
                .toArray(MethodSelector[]::new);
    }

    @Feature("Перезапуск упавших тестов")
    @Description("Если в предыдущем запуске тестов были флаки тесты, этот тест перезапускает их")
    @Severity(value = SeverityLevel.NORMAL)
    @Test
    @Issue("UI-WAY2 №1")
    @DisplayName("Перезапуск упавших тестов")
    public void rerunTest() {
        Launcher launcher = LauncherFactory.create();

        Set<String> failedTests = FailedTestRecorder.getFailedTests();

        if (failedTests.isEmpty()) {
            System.out.println("No failed tests to rerun.");
            return;
        }
        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
                .selectors(selectFailedTests(failedTests))
                .build();
        SummaryGeneratingListener listener = new SummaryGeneratingListener();
        launcher.registerTestExecutionListeners(listener);
        launcher.execute(request);
        System.out.println("Tests failed: " + failedTests);
        listener.getSummary().printTo(new PrintWriter(System.out));
    }
}