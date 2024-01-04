package tests.ui;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import utils.CucumberExtension;

@ExtendWith(CucumberExtension.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "path.to.step.definitions",
        plugin = {"pretty", "html:target/cucumber-reports"}
)
public class TestEntry {

    @BeforeEach
    public void setUp() {
        // Perform initialization here if needed for each test method
    }

    @AfterEach
    public void tearDown() {
        // Perform cleanup here if needed for each test method
    }
}

