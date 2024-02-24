package com.ecommerce.pages;

import com.ecommerce.base.BasePage;
import com.ecommerce.utils.ConfigUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.ecommerce.constants.UrlPathConstants.CLIENT_PATH;
import static com.ecommerce.locators.LoginLocators.*;

public class LoginPage extends BasePage {

    @FindBy(id = USER_EMAIL_ID)
    WebElement mail_input;

    @FindBy(id = USER_PASSWORD_ID)
    WebElement password_input;

    @FindBy(id = LOGIN_BTN_ID)
    WebElement login_button;

    @FindBy(xpath = ERROR_MESSAGE_XPATH)
    WebElement errorMessage_text;


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step
    public ProductsPage login(String email, String pass){
        elementInteraction
                .setInput(mail_input, email)
                .setInput(password_input, pass)
                .simpleClick(login_button);

        return new ProductsPage(driver);
    }
    @Step
    public LoginPage load(){
        driver.get(ConfigUtils.getBaseUrl() + CLIENT_PATH);
        return this;
    }

    @Step
    public String getErrorMessage(){
        return errorMessage_text.getText();
    }

}
