package com.ecommerce.pages;

import com.ecommerce.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ThankYouPage extends BasePage {


    @FindBy(className = "hero-primary")
    WebElement thankYouPage;


    public ThankYouPage(WebDriver driver) {
        super(driver);
    }

    public Boolean isThankYouForPlacingOrderDisplayed(){
        return thankYouPage.isDisplayed();
    }

}
