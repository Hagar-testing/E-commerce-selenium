package com.ecommerce.pages;

import com.ecommerce.base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import static com.ecommerce.locators.CheckoutPageLocators.*;


public class CheckoutPage extends BasePage {

    @FindBy(css = SELECT_COUNTRY_INPUT_CSS)
    WebElement selectCountry_input;

    @FindBy(css = PLACE_ORDER_BUTTON_CSS)
    WebElement placeOrder_button;

    @FindBy(className = SEARCH_RESULTS_CLASS_NAME)
    WebElement searchResults_text;

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }


    @Step
    public CheckoutPage selectCountry(String country) {

        elementInteraction
                .setInputWithJavaScriptExecutor(selectCountry_input, country)
                .simulateBackspace(selectCountry_input)
                .javascriptClick(searchResults_text);
        return this;
    }

    @Step
    public ThankYouPage clickPlaceOrderButton() {
        elementInteraction.javascriptClick(placeOrder_button);
        return new ThankYouPage(driver);
    }


}
