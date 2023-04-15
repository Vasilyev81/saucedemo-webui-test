package com.theandrey;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;


public class SwagSignInTest {
    WebDriver driver = new ChromeDriver();

    @Test
    public void loginShouldBeSuccessful() throws InterruptedException {
        driver.get("https://www.saucedemo.com/");
        WebElement unameInput = driver.findElement(By.id("user-name"));
        unameInput.sendKeys(TD.standard_username);
        WebElement pwdInput = driver.findElement(By.xpath("//input[@name='password']"));
        pwdInput.sendKeys(TD.valid_pwd);
        WebElement loginButton = driver.findElement(By.xpath("//input[@name='login-button']"));
        loginButton.click();
        Assert.assertEquals(1, driver.findElements(By.className("shopping_cart_link")).size());
        WebElement burgerMenu = driver.findElement(By.xpath("//button[text()='Open Menu']"));
        burgerMenu.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Logout")));
        WebElement logout = driver.findElement(By.linkText("Logout"));
        logout.click();
        Assert.assertEquals("Not logged out", 1, driver.findElements(By.xpath("//input[@id='login-button']")).size());
    }

    @Test
    public void addProductToCart() {
        driver.get("https://www.saucedemo.com/");
        WebElement unameInput = driver.findElement(By.id("user-name"));
        unameInput.sendKeys(TD.standard_username);
        WebElement pwdInput = driver.findElement(By.xpath("//input[@name='password']"));
        pwdInput.sendKeys(TD.valid_pwd);
        WebElement loginButton = driver.findElement(By.xpath("//input[@name='login-button']"));
        loginButton.click();
        WebElement addToCartButton = driver.findElement(By.xpath("//div[text()='Sauce Labs Bike Light']//ancestor::div[@class='inventory_item']//button[text()='Add to cart']"));
        addToCartButton.click();
        WebElement shoppingCart = driver.findElement(By.xpath("//a[@class='shopping_cart_link']"));
        Assert.assertEquals("Badge not appeared on shopping cart", "1", shoppingCart.findElement(By.className("shopping_cart_badge")).getText());
        shoppingCart.click();
        String currentUrl = driver.getCurrentUrl();
        System.out.println("Url is: " + currentUrl);
        Assert.assertEquals("Wrong (not a cart) url", "https://www.saucedemo.com/cart.html", currentUrl);
        List<WebElement> cartItems = driver.findElements(By.className("cart_item"));
        Assert.assertEquals(1, cartItems.size());
        WebElement cartItemName = cartItems.get(0).findElement(By.className("inventory_item_name"));
        Assert.assertEquals("Wrong item label", "Sauce Labs Bike Light", cartItemName.getText());
        WebElement cartQuantity = cartItems.get(0).findElement(By.className("cart_quantity"));
        Assert.assertEquals("Wrong item quantity", "1", cartQuantity.getText());
        WebElement removeBtn = cartItems.get(0).findElement(By.xpath("//*[text()='Remove']"));
    }

    @After
    public void tearDown() {
        driver.close();
    }
}
