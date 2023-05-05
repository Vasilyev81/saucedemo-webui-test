package com.theandrey.pageobjects;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends BasePage {

    @FindBy(className = "cart_item")
    private List<WebElement> cartProducts;

    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public int numberOfProducts() {
        return cartProducts.size();
    }

    public String productInfo(int index, @NotNull String infoType) {
        WebElement  cartProduct= cartProducts.get(index - 1);
        return switch (infoType) {
            case ("label") -> cartProduct.findElement(By.className("inventory_item_name")).getText();
            case ("quantity") -> cartProduct.findElement(By.className("cart_quantity")).getText();
            default -> throw new IllegalStateException("Not defined infoType name: " + infoType);
        };
    }
}