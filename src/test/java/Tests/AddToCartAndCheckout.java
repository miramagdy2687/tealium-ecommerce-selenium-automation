package Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class AddToCartAndCheckout extends BasePage {
    @Test
    public void addProductToCart() throws InterruptedException {

        // PRECONDITION: User must be logged in
        login();

        // Hover over Accessories
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement accessories = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//a[normalize-space()='Accessories']")
                )
        );

        Actions actions = new Actions(driver);
        actions.moveToElement(accessories).perform();
        // Click Shoes
        WebElement shoes = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//a[normalize-space()='Shoes']")
                )
        );
        shoes.click();
        // Wait for Accessories page
        wait.until(ExpectedConditions.urlContains("accessories"));
        //Scroll to View Items
        WebElement firstShoes =
                driver.findElement(By.xpath("(//*[normalize-space()='View Details'])[1]"));
        Actions scrollAction = new Actions(driver);
        scrollAction.moveToElement(firstShoes)
                .scrollByAmount(0, 300)
                .perform();
        ;
        // View first shoes Details
        firstShoes.click();
        // Wait until product details page is displayed
        wait.until(
                ExpectedConditions.urlContains(
                        "barclay-d-orsay-pump-nude"
                )
        );
        //Select shoe size
        WebElement shoeSize = driver.findElement(By.id("swatch100"));
        scrollAction.moveToElement(shoeSize)
                .scrollByAmount(0, 200)
                .perform();
        shoeSize.click();
        //Select Shoe color
        WebElement shoeColor = driver.findElement(By.id("swatch14"));
        shoeColor.click();
        //Click Add to cart button
        WebElement addToCartButton = driver.findElement(By.className("add-to-cart-buttons"));
        addToCartButton.click();
        //Verify Shopping Cart Opened
        WebElement shoppingScreen = driver.findElement(By.xpath("//h1[normalize-space()='Shopping Cart']"));
        Assert.assertTrue(
                shoppingScreen.isDisplayed()
        );
        //Click Proceed to checkout
        WebElement proceedButton =
                driver.findElement(By.xpath("//ul[@class='checkout-types top']//button[@title='Proceed to Checkout']"));
        proceedButton.click();
        // Fill out All mandatory address fields
        // Add Address
        WebElement address = driver.findElement(By.id("billing:street1"));
        address.clear();
        address.sendKeys("123 Test Street");

// Add City
        WebElement city = driver.findElement(By.id("billing:city"));
        city.clear();
        city.sendKeys("Cairo");

// ZIP / Postal Code
        WebElement zip = driver.findElement(By.id("billing:postcode"));
        zip.clear();
        zip.sendKeys("11511");

// Telephone
        WebElement telephone = driver.findElement(By.id("billing:telephone"));
        telephone.clear();
        telephone.sendKeys("01012345678");
//Select State
        WebElement country = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("billing:country_id")
                )
        );

        Select countrySelect = new Select(country);
        countrySelect.selectByVisibleText("United States");
        // Select Country
        WebElement state = driver.findElement(By.id("billing:region_id"));

        Select stateSelect = new Select(state);
        stateSelect.selectByVisibleText("California");
//Click Ship to this address
        WebElement shipToThisAddress =
                driver.findElement(By.id("billing:use_for_shipping_yes"));
        shipToThisAddress.click();

        // Click on Continue Button
        WebElement continueButton =
                driver.findElement(By.xpath("//button[contains(.,'Continue')]"));
        scrollAction.moveToElement(continueButton)
                .scrollByAmount(0, 200)
                .perform();
        continueButton.click();
// wait until shipping Method loaded
        Thread.sleep(3000);

// Select Free Shipping
        WebElement freeShipping =
                driver.findElement(By.id("s_method_freeshipping_freeshipping"));

        freeShipping.click();
// Click Continue in Shipping Method section
        WebElement continueShipping =
                driver.findElement(By.xpath("//div[@id='shipping-method-buttons-container']//button[contains(.,'Continue')]"));

        continueShipping.click();

        // Click Continue in Payment information Step
        Thread.sleep(2000);
        WebElement continuePaymentButton =
                driver.findElement(By.xpath("//button[@onclick='payment.save()']"));
        continuePaymentButton.click();

        //Click Place Order button
        Thread.sleep(2000);
        WebElement placeOrderButton =
                driver.findElement(By.xpath("//button[@title='Place Order']"));
        scrollAction.moveToElement(placeOrderButton)
                .scrollByAmount(0, 100)
                .perform();
        placeOrderButton.click();
        //Verify success message
        Thread.sleep(2000);

        Assert.assertTrue(
                driver.getCurrentUrl().contains("/checkout/onepage/success/"));
        // Close browser
        driver.quit();
    }

}
