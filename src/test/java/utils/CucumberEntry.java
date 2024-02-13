package utils;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "steps",
        tags = "@all",
        dryRun = false,
        snippets = CucumberOptions.SnippetType.UNDERSCORE
)
public class CucumberEntry {
}
