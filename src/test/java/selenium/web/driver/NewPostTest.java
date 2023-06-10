package selenium.web.driver;

import static selenium.web.driver.SignInTest.login;

import java.time.Duration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class NewPostTest {
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
    public void testNewPost() {
        login(driver);
        driver.findElement(By.id("newPostLink")).click();
        driver.findElement(By.id("postTheme")).clear();
        driver.findElement(By.id("postTheme")).sendKeys("PostSel");
        driver.findElement(By.id("postContent")).click();
        driver.findElement(By.id("postContent")).clear();
        driver.findElement(By.id("postContent")).sendKeys("PostSel");
        driver.findElement(By.id("createPostButton")).click();
        driver.close();
    }

    @AfterEach
    public void quit() {
        driver.quit();
    }
}