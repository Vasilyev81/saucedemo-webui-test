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

    @After
    public void tearDown() {
        driver.close();
    }
}
