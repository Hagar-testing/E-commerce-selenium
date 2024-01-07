package com.ecommerce.pages;

import com.ecommerce.base.BasePage;
import com.ecommerce.locators.CheckoutPageLocators;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import static com.ecommerce.locators.CheckoutPageLocators.*;


public class CheckoutPage extends BasePage {

    @FindBy(css = SELECT_COUNTRY_INPUT_CSS)
    WebElement selectCountryElement;

    @FindBy(css =PLACE_ORDER_BUTTON_CSS)
    WebElement placeOrderBtn;

    @FindBy(className = SEARCH_RESULTS_CLASS_NAME)
    WebElement searchResults;



    public CheckoutPage(WebDriver driver) {
        super(driver);
    }



    public CheckoutPage selectCountry(String country)  {

        javascriptExecutorUtils.sendInput(selectCountryElement,country);
        selectCountryElement.sendKeys(Keys.BACK_SPACE);

        waitUtils.waitForVisibilityOfElement(searchResults);
        searchResults.click();
        return this;
    }

 
    public ThankYouPage clickPlaceOrderButton(){
        javascriptExecutorUtils.executeJavaScriptClick(placeOrderBtn);
        return new ThankYouPage(driver);
    }


}
