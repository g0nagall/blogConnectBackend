package selenium.web.driver;

import java.time.Duration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SignInTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
    }

    @Test
    public void testSignIn() {
        login(driver);
        driver.close();
    }

    public static void login(WebDriver webDriver){
        webDriver.get("http://localhost:3000/");
        webDriver.findElement(By.id("email")).clear();
        webDriver.findElement(By.id("email")).sendKeys("test@test");
        webDriver.findElement(By.id("password")).click();
        webDriver.findElement(By.id("password")).clear();
        webDriver.findElement(By.id("password")).sendKeys("test");
        webDriver.findElement(By.id("loginButton")).click();
    }

    @AfterEach
    public void quit() {
        driver.quit();
    }
}