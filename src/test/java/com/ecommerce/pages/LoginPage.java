package com.ecommerce.pages;

import com.ecommerce.base.BasePage;
import com.ecommerce.utils.ConfigUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.ecommerce.constants.UrlPathConstants.CLIENT_PATH;
import static com.ecommerce.locators.LoginLocators.*;

public class LoginPage extends BasePage {

    @FindBy(id = USER_EMAIL_ID)
    WebElement mail;

    @FindBy(id = USER_PASSWORD_ID)
    WebElement password;

    @FindBy(id = LOGIN_BTN_ID)
    WebElement loginBtn;

    @FindBy(xpath = ERROR_MESSAGE_XPATH)
    WebElement errorMessage;


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public ProductsPage login(String email, String pass){
        mail.sendKeys(email);
        password.sendKeys(pass);
        loginBtn.click();
        return new ProductsPage(driver);
    }
    public LoginPage load(){
        driver.get(ConfigUtils.getBaseUrl() + CLIENT_PATH);
        return this;
    }

    public String getErrorMessage(){
        waitUtils.waitForVisibilityOfElement(errorMessage);
        return errorMessage.getText();
    }

}
