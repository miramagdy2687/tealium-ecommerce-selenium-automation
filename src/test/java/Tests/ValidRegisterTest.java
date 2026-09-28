package Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Random;

public class ValidRegisterTest {

    WebDriver driver;

    @BeforeTest
    public void setUp() {
        driver = new ChromeDriver();
        driver.navigate().to("https://ecommerce.tealiumdemo.com/customer/account/create/");
        driver.manage().window().maximize();
    }

    @Test
    public void Register() {
        // Close popup
        driver.findElement(By.id("privacy_pref_optin")).click();
        driver.findElement(By.id("consent_prompt_submit")).click();

        // Enter First Name
        WebElement firstName = driver.findElement(By.id("firstname"));
        firstName.sendKeys("NewUser");

        // Enter Last Name
        WebElement lastName = driver.findElement(By.id("lastname"));
        lastName.sendKeys("Test");

        // Enter Email
        Random random = new Random();
        int randomNumber = random.nextInt(9000);
        String randomEmail = "user_new1" + randomNumber + "@gmail.com";
        driver.findElement(By.id("email_address"))
                .sendKeys(randomEmail);

        // Enter Password
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("Test@123456789!");

        // Confirm Password
        WebElement confirmPassword = driver.findElement(By.id("confirmation"));
        confirmPassword.sendKeys("Test@123456789!");


        // Click Register
        WebElement element = driver.findElement(By.cssSelector("button[title='Register']"));
        Actions actions = new Actions(driver);

        actions.scrollToElement(element).perform();
        Actions action = new Actions(driver);

// Scroll down by 1000 pixels vertically
        action.scrollByAmount(0, 1000).perform();
        element.click();
        // Verify registration

        WebElement successfulRegister = driver.findElement(By.className("welcome-msg"));
        Assert.assertTrue(
                successfulRegister.isDisplayed()
        );

        // Close browser
        driver.quit();
    }
}