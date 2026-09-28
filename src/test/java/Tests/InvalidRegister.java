package Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class InvalidRegister {
    WebDriver driver;

    @BeforeTest
    public void setUp() {
        driver = new ChromeDriver();
        driver.navigate().to("https://ecommerce.tealiumdemo.com/customer/account/create/");
        driver.manage().window().maximize();
    }

    @Test
    public void InvalidRegister() throws InterruptedException {
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
        WebElement email =
                driver.findElement(By.id("email_address"));
        email.sendKeys("test12@hhhh.rrr");

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
        Thread.sleep(2000);
// Verify error message
        WebElement emailError =
                driver.findElement(By.xpath("(//span[normalize-space()='\"Email\" is not a valid hostname.'])[1]"));

        Assert.assertTrue(
                emailError.isDisplayed());
        // Close browser
        driver.quit();
    }
}
