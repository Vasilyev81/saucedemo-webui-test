package com.theandrey.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage extends BasePage {

    @FindBy(xpath = "//button[text()='Open Menu']")
    private WebElement burgerMenu;

    @FindBy(linkText = "Logout")
    private WebElement logoutLink;

    @FindBy(xpath = "//a[@class='shopping_cart_link']")
    private WebElement shoppingCart;

    @FindBy(className = "shopping_cart_badge") private WebElement shoppingCartBadge;

    @FindBy(xpath = "//div[text()='Sauce Labs Bike Light']//ancestor::div[@class='inventory_item']//button[text()='Add to cart']")
    private WebElement bikeLightAddToCartButton;


    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickOnBurger() {
        burgerMenu.click();
        WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofMillis(700));
        driverWait.until(ExpectedConditions.visibilityOf(logoutLink));
    }

    public LogInPage signOut() {
        logoutLink.click();
        return new LogInPage(driver);
    }

    public boolean verify() {
        return driver.getCurrentUrl().equals("https://www.saucedemo.com/inventory.html") &
                burgerMenu.isDisplayed() & burgerMenu.isEnabled() &
                shoppingCart.isDisplayed() & shoppingCart.isEnabled();
    }

    public void addBikeLightToCart() {
        bikeLightAddToCartButton.click();
    }

    public String shoppingCartBadgeText() {
        return shoppingCartBadge.getText();
    }
    public CartPage clickOnShoppingCart() {
        shoppingCart.click();
        return new CartPage(driver);
    }
}
