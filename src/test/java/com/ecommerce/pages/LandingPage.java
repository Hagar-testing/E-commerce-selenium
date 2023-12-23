package com.ecommerce.pages;

import com.ecommerce.base.BasePage;
import com.ecommerce.utils.ConfigUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LandingPage extends BasePage {

    @FindBy(id = "userEmail")
    WebElement mail;
    @FindBy(id = "userPassword")
    WebElement password;
    @FindBy(id = "login")
    WebElement loginBtn;
    @FindBy(xpath = "//div[@class='ng-tns-c4-0 toast-message ng-star-inserted']")
    WebElement errorMessage;

    public LandingPage(WebDriver driver) {
        super(driver);
    }

    public ProductsPage login(String email, String pass){
        mail.sendKeys(email);
        password.sendKeys(pass);
        loginBtn.click();
        return new ProductsPage(driver);
    }
    public LandingPage load(){
        driver.get(ConfigUtils.getBaseUrl() + "client/");
        return this;
    }

    public String getErrorMessage(){
        waitUtils.waitForVisibilityOfElement(errorMessage);
        return errorMessage.getText();
    }

}
