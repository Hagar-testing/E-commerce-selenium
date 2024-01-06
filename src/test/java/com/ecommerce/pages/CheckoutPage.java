package com.ecommerce.pages;

import com.ecommerce.base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import static java.lang.Thread.sleep;

public class CheckoutPage extends BasePage {

    @FindBy(css = "input[placeholder='Select Country']")
    WebElement selectCountryElement;

    @FindBy(css = "a[class='btnn action__submit ng-star-inserted']")
    WebElement placeOrderBtn;

    @FindBy(css = "section[class='ta-results list-group ng-star-inserted']")
    WebElement dropdownListLocator;


    @FindBy(className = "fa-search")
    WebElement searchResults;

    By resultLocator = By.cssSelector("button");

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
