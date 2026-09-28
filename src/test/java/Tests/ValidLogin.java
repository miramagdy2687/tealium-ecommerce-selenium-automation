package Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ValidLogin {
    WebDriver driver;

    @BeforeTest
    public void setUp() {
        driver = new ChromeDriver();
        driver.navigate().to("https://ecommerce.tealiumdemo.com/customer/account/login/");
        driver.manage().window().maximize();
    }

    @Test
    public void loginTest() {
        // Close popup
        driver.findElement(By.id("privacy_pref_optin")).click();
        driver.findElement(By.id("consent_prompt_submit")).click();
        //Add login data
        WebElement emailField = driver.findElement(By.id("email"));
        emailField.clear();
        emailField.sendKeys("test123@test111.com");

        WebElement passwordField = driver.findElement(By.id("pass"));
        passwordField.clear();
        passwordField.sendKeys("1234567");

        // Click Sign In
        WebElement loginButton = driver.findElement(By.id("send2"));
        // Scroll to Login button
        Actions scrollAction = new Actions(driver);
        scrollAction.scrollByAmount(0, 500).perform();
        ;
        loginButton.click();
        //Verify login successfully
        WebElement userName = driver.findElement(By.className("welcome-msg"));
        Assert.assertTrue(
                userName.isDisplayed()
        );

        // Close browser
        driver.quit();
    }

}