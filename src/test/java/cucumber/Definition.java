package cucumber;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.time.Duration;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Definition {
    private String email;
    private String password;
    private WebDriver driver;
    private final Map<String, String> elementId =
            Map.ofEntries(
                    Map.entry("Log In", "loginButton"),
                    Map.entry("View", "detailsButton"),
                    Map.entry("Logout", "header-log-oug-button"),
                    Map.entry("Forgot Password", "forgotPassword"),
                    Map.entry("Change Password", "submitEmail"),
                    Map.entry("Create Post", "newPostLink"),
                    Map.entry("Comment", "commentarySubmitButton"),
                    Map.entry("Create", "createPostButton"),
                    Map.entry("Details", "detailsButton"),
                    Map.entry("Modify info", "modifyInfoButton"),
                    Map.entry("Profile", "myProfile"),
                    Map.entry("Modify", "modifyDataButton"),
                    Map.entry("Register", "registerButton"));

    private final Map<String, String> endpoints =
            Map.of(
                    "Login",
                    "login",
                    "Registration",
                    "register",
                    "Profile",
                    "profile",
                    "Create Post",
                    "post/create",
                    "All Posts",
                    "posts");

    @Before
    public void init() {
        email = "test@test";
        password = "test";
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        driver.get("http://localhost:3000");
    }

    @Given("I am a registered user")
    public void iAmARegisteredUser() {
        email = "test@test";
        password = "test";
    }

    @And("I navigate to the {string} page")
    public void iNavigateToThePage(String arg0) {
        driver.get("http://localhost:3000/" + endpoints.get(arg0));
    }

    @When("I fill in {string} with {string}")
    public void iFillInWith(String arg0, String arg1) {
        int buff = driver.findElement(By.id(arg0)).getAttribute("value").length();
        for (int i = 0; i < buff; i++) {
            driver.findElement(By.id(arg0)).sendKeys(Keys.BACK_SPACE);
        }
        findByIdSendKeys(arg0, arg1);
    }

    @And("I click on the {string} button")
    public void iClickOnTheButton(String arg0) {
        findByIdAndClick(elementId.get(arg0));
    }

    @Then("I should be successfully logged in")
    public void iShouldBeSuccessfullyLoggedIn() {
        assertEquals(driver.getCurrentUrl(), "http://localhost:3000/login");
        driver.close();
    }

    @Then("I should see {string} message for {string} field on {string} page")
    public void iShouldSeeMessageForFieldOnPage(String arg0, String arg1, String arg2) {
        assertEquals(driver.findElement(By.id(arg1 + "-helper-text")).getText(), arg0);
        driver.close();
    }

    @Given("I am a logged in user")
    public void iAmALoggedInUser() {
        findByIdAndClick("header-login-button");
        findByIdSendKeys("email", email);
        findByIdSendKeys("password", password);
        findByIdAndClick("loginButton");
    }

    @When("I click on {string} button")
    public void iClickOnButton(String arg0) {
        findByIdAndClick(elementId.get(arg0));
    }

    @Then("I should be successfully logged out")
    public void iShouldBeSuccessfullyLoggedOut() {
        assertEquals(driver.getCurrentUrl(), "http://localhost:3000/login");
    }

    @And("I should land on the {string} page")
    public void iShouldLandOnThePage(String arg0) {
        assertEquals(driver.getCurrentUrl(), "http://localhost:3000/" + endpoints.get(arg0));
    }

    @And("I click {string} link")
    public void iClickLink(String arg0) {
        findByIdAndClick(elementId.get(arg0));
    }

    @And("I should see the new blog listing on the Homepage")
    public void iShouldSeeTheNewBlogListingOnTheHomepage() {
        findByIdAndClick("detailsButton");
        assertTrue(driver.getCurrentUrl().contains("/post"));
    }

    @Then("I should be successfully registered")
    public void iShouldBeSuccessfullyRegistered() {
        assertEquals(driver.getCurrentUrl(), "http://localhost:3000/login");
    }

    private void findByIdAndClick(String id) {
        driver.findElement(By.id(id)).click();
    }

    private void findByIdSendKeys(String id, String keys) {
        driver.findElement(By.id(id)).sendKeys(keys);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}