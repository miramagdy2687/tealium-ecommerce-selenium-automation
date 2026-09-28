package Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class invalidLogin {
    WebDriver driver;

    @BeforeTest
    public void setUp() {
        driver = new ChromeDriver();
        driver.navigate().to("https://ecommerce.tealiumdemo.com/customer/account/login/");
        driver.manage().window().maximize();
    }

    @Test
    public void invalidLoginTest() {

        // Close popup
        driver.findElement(By.id("privacy_pref_optin")).click();
        driver.findElement(By.id("consent_prompt_submit")).click();
        driver.manage().window().maximize();

        // Enter invalid email
        driver.findElement(By.id("email"))
                .sendKeys("tennnstfff@gg1test111.com");

        // Enter password
        driver.findElement(By.id("pass"))
                .sendKeys("1234567");

        // Click Sign In
        WebElement loginButton = driver.findElement(By.id("send2"));
        // Scroll to Login button
        Actions scrollAction = new Actions(driver);
        scrollAction.scrollByAmount(0, 500).perform();
        ;
        loginButton.click();

        // Verify Error msg
        WebElement errorMsg = driver.findElement(By.xpath("//span[normalize-space()='Invalid login or password.']"));
        Assert.assertTrue(
                errorMsg.isDisplayed()
        );
        //  Close browser
        driver.quit();
    }
}