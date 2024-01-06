package com.ecommerce.pages;

import com.ecommerce.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutPage extends BasePage {

    @FindBy(css = "input[placeholder='Select Country']")
    WebElement selectCountryElement;

    @FindBy(css = "a[class='btnn action__submit ng-star-inserted']")
    WebElement placeOrderBtn;

    @FindBy(css = "section[class='ta-results list-group ng-star-inserted']")
    WebElement dropdownListLocator;

    By resultLocator = By.cssSelector("button");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }



    public CheckoutPage selectCountry(String country){
        selectCountryElement.sendKeys(country);
        WebElement results = waitUtils.waitForVisibilityOfElement(dropdownListLocator);
        results.findElement(resultLocator).click();
        return this;
    }

    public void clickPlaceOrderButton(){
        javascriptExecutorUtils.executeJavaScriptClick(placeOrderBtn);
    }


}
