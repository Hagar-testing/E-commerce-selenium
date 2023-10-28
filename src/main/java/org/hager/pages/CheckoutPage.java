package org.hager.pages;

import org.hager.utils.webdriver.JavascriptExecutorUtils;
import org.hager.utils.webdriver.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage {

    private final WaitUtils waitUtils;

    private final JavascriptExecutorUtils javascriptExecutorUtils;

    @FindBy(css = "input[placeholder='Select Country']")
    WebElement selectCountryElement;

    @FindBy(css = "a[class='btnn action__submit ng-star-inserted']")
    WebElement placeOrderBtn;

    @FindBy(css = "section[class='ta-results list-group ng-star-inserted']")
    WebElement dropdownListLocator;
    By resultLocator = By.cssSelector("button");

    public CheckoutPage(WebDriver driver,WaitUtils waitUtils, JavascriptExecutorUtils javascriptExecutorUtils) {
        this.waitUtils = waitUtils;
        this.javascriptExecutorUtils = javascriptExecutorUtils;
        PageFactory.initElements(driver,this);
    }



    public void selectCountry(String country){
        selectCountryElement.sendKeys(country);
        WebElement results = waitUtils.waitForVisibilityOfElement(dropdownListLocator);
        results.findElement(resultLocator).click();
    }

    public void selectPlaceOrderButton(){
        javascriptExecutorUtils.executeJavaScriptClick(placeOrderBtn);
    }
}
