package Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeMethod
    public void setUp() {

        driver = new ChromeDriver();

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.manage().window().maximize();

        driver.get("https://ecommerce.tealiumdemo.com/customer/account/login/");
    }

    protected void login() {

        // Close privacy popup
        WebElement privacy = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.id("privacy_pref_optin")
                )
        );

        privacy.click();

        WebElement submit = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.id("consent_prompt_submit")
                )
        );

        submit.click();


        // Email
        WebElement emailField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("email")
                )
        );

        emailField.clear();
        emailField.sendKeys("user_new1604@gmail.com");

        // Password
        WebElement passwordField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("pass")
                )
        );

        passwordField.clear();
        passwordField.sendKeys("Test@123456789!");

        // Sign In
        WebElement loginButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.id("send2")
                )
        );
        // Scroll to Login button
        Actions scrollAction = new Actions(driver);
        scrollAction.scrollByAmount(0, 500).perform();
        ;

        loginButton.click();

        // Verify successful login
        WebElement welcomeMessage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.className("welcome-msg")
                )
        );

        Assert.assertTrue(welcomeMessage.isDisplayed());
    }

    // @AfterMethod
    //  public void tearDown() {

    //     if (driver != null) {
    //        driver.quit();
    //    }
    // }
}