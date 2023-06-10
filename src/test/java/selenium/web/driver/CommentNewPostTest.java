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

public class CommentNewPostTest {
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
    public void testCommentNewPost() {
        login(driver);
        driver.findElement(By.id("postsLink")).click();
        driver.findElement(By.xpath("(//div[@id='detailsButton']/div/div[2])[4]")).click();
        driver.findElement(By.cssSelector(".MuiFormControl-root")).click();
        driver.findElement(By.id("commentary")).click();
        driver.findElement(By.id("commentary")).clear();
        driver.findElement(By.id("commentary")).sendKeys("PostSEl");
        driver.findElement(By.id("commentarySubmitButton")).click();
        driver.close();
    }

    @AfterEach
    public void quit() {
        driver.quit();
    }
}