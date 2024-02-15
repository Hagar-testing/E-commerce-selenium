package com.ecommerce.pages;

import com.ecommerce.base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.ecommerce.locators.ProductsLocators.ADD_TO_CART_BUTTON_XPATH;
import static com.ecommerce.locators.ThankYouPageLocators.THANK_YOU_PAGE_CLASS_NAME;

public class ThankYouPage extends BasePage {

    @FindBy(className = THANK_YOU_PAGE_CLASS_NAME)
    WebElement thankYou_text;

    public ThankYouPage(WebDriver driver) {
        super(driver);
    }

    @Step
    public Boolean isThankYouForPlacingOrderDisplayed() {
        return thankYou_text.isDisplayed();
    }

}
