package com.theandrey.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogInPage extends BasePage {
    @FindBy(xpath = "//input[@name='user-name']")
    private WebElement usernameField;
    @FindBy(xpath = "//input[@name='password']")
    private WebElement passwordField;
    @FindBy(xpath = "//input[@name='login-button']")
    private WebElement loginBtn;

    public LogInPage(WebDriver driver) {
        super(driver);
        driver.get("https://www.saucedemo.com/");
        PageFactory.initElements(driver, this);
    }

    public void fillInUsername(String username) {
        usernameField.sendKeys(username);
    }

    public void fillInPassword(String password) {
        passwordField.sendKeys(password);
    }

    public MainPage clickLogin() {
        loginBtn.click();
        return new MainPage(driver);
    }

    public boolean verify() {
        return driver.getCurrentUrl().equals("https://www.saucedemo.com/") &
                loginBtn.isDisplayed() & loginBtn.isEnabled();
    }
}
