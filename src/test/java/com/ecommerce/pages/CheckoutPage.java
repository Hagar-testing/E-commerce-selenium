package com.ecommerce.pages;

import com.ecommerce.base.BasePage;
import com.ecommerce.utils.ElementInteraction;
import io.qameta.allure.Step;
import org.openqa.selenium.*;

import static com.ecommerce.locators.CheckoutPageLocators.*;


public class CheckoutPage extends BasePage {

    private final By selectCountry_input = By.cssSelector(SELECT_COUNTRY_INPUT_CSS);
    private final By placeOrder_button = By.cssSelector(PLACE_ORDER_BUTTON_CSS);
    private final By searchResults_input = By.className(SEARCH_RESULTS_CLASS_NAME);

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }


    @Step
    public CheckoutPage selectCountry(String country)  {

        new ElementInteraction(driver)
                .setInputWithJavaScriptExecutor(selectCountry_input,country)
                .simulateBackspace(selectCountry_input)
                .javascriptClick(searchResults_input);
        return this;
    }

    @Step
    public ThankYouPage clickPlaceOrderButton(){
        new ElementInteraction(driver).javascriptClick(placeOrder_button);
        return new ThankYouPage(driver);
    }


}
