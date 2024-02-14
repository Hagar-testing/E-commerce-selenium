package com.ecommerce.pages;

import com.ecommerce.base.BasePage;
import com.ecommerce.utils.ElementInteraction;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.ecommerce.locators.CartPageLocators.CHECKOUT_BUTTON_XPATH;
import static com.ecommerce.locators.CartPageLocators.PRODUCT_TITLE_XPATH;

public class CartPage extends BasePage {

    private final By checkout_button = By.xpath(CHECKOUT_BUTTON_XPATH);
    private final By products_list = By.xpath(PRODUCT_TITLE_XPATH);

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Step
    public CheckoutPage clickOnCheckoutButton() {
        new ElementInteraction(driver).javascriptClick(checkout_button);
        return new CheckoutPage(driver);
    }

    @Step
    public Boolean isCartContainsProducts(){
        List<WebElement> productsInCart = driver.findElements(products_list);
        return productsInCart.size() > 0;
    }
}
