package com.theandrey;

import com.theandrey.categories.Major;
import com.theandrey.pageobjects.CartPage;
import com.theandrey.pageobjects.LogInPage;
import com.theandrey.pageobjects.MainPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CartTest {
    WebDriver driver = new ChromeDriver();

    @Category({Major.class})
    @Test
    public void addProductToCart() throws InterruptedException {
        LogInPage logInPage = new LogInPage(driver);
        logInPage.fillInUsername(TD.standard_username);
        logInPage.fillInPassword(TD.valid_pwd);
        MainPage mainPage = logInPage.clickLogin();
        mainPage.addBikeLightToCart();
        Assert.assertEquals("Badge wrong value or not appeared on shopping cart", "1", mainPage.shoppingCartBadgeText());
        CartPage cartPage = mainPage.clickOnShoppingCart();
        Assert.assertEquals(1, cartPage.numberOfProducts());
        Assert.assertEquals("Wrong item label", "Sauce Labs Bike Light", cartPage.productInfo(1, "label"));
        Assert.assertEquals("Wrong item quantity", "1", cartPage.productInfo(1, "quantity"));
// WebElement removeBtn = cartItems.get(0).findElement(By.xpath("//*[text()='Remove']"));
    }

    @After
    public void tearDown() {
        driver.close();
    }

}
