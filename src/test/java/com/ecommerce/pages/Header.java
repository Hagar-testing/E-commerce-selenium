package com.ecommerce.pages;

import com.ecommerce.base.BasePage;

import com.ecommerce.utils.ElementInteraction;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.ecommerce.locators.HeaderLocators.PRODUCTS_COUNT_CART_XPATH;

public class Header extends BasePage {
    @FindBy(xpath = PRODUCTS_COUNT_CART_XPATH)
    WebElement productsCountCart_text;

    public Header(WebDriver driver) {
        super(driver);
    }

    @Step
    public CartPage goToCartPage(){
        elementInteraction.javascriptClick(productsCountCart_text);
        return new CartPage(driver);
    }
    @Step
    public int getCartItemsCount() throws InterruptedException {
        Thread.sleep(1000);
        return Integer.parseInt(productsCountCart_text.getText());
    }


}
