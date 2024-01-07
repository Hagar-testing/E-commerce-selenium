package com.ecommerce.pages;

import com.ecommerce.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import static com.ecommerce.locators.ThankYouPageLocators.THANK_YOU_PAGE_CLASS_NAME;

public class ThankYouPage extends BasePage {

    @FindBy(className = THANK_YOU_PAGE_CLASS_NAME)
    WebElement thankYouPage;


    public ThankYouPage(WebDriver driver) {
        super(driver);
    }

    public Boolean isThankYouForPlacingOrderDisplayed(){
        return thankYouPage.isDisplayed();
    }

}
