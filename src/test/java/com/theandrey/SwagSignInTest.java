package com.theandrey;

import com.theandrey.categories.Critical;
import com.theandrey.pageobjects.CartPage;
import com.theandrey.pageobjects.LogInPage;
import com.theandrey.pageobjects.MainPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class SwagSignInTest {
    WebDriver driver = new ChromeDriver();

    @Category({Critical.class})
    @Test
    public void loginShouldBeSuccessful() {
        LogInPage logInPage = new LogInPage(driver);
        logInPage.fillInUsername(TD.standard_username);
        logInPage.fillInPassword(TD.valid_pwd);
        MainPage mainPage = logInPage.clickLogin();
        Assert.assertTrue("Not a main page",mainPage.verify());
        mainPage.clickOnBurger();
        LogInPage logInPage1 = mainPage.signOut();
        Assert.assertTrue("Not a login page", logInPage1.verify());
    }

    @After
    public void tearDown() {
        driver.close();
    }
}
