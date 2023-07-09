package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        tags = "@Test",
        glue="steps",
        plugin = { "pretty", "html:target/cucumber/report.html","json:target/cucumber/report.json" },
        publish = true
)

public class Testrunner {
}