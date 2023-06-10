package cucumber;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = org.openqa.selenium.grid.config.Config.class)
public class CucumberConfigConfig {}