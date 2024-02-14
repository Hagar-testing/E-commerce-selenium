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

    private final By mail_input = By.id(USER_EMAIL_ID);
    private final By password_input = By.id(USER_PASSWORD_ID);
    private final By login_button= By.id(LOGIN_BTN_ID);
    private final By errorMessage_text = By.xpath(ERROR_MESSAGE_XPATH);


    public LoginPage(WebDriver driver) {
        super(driver);
    }

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
        return elementInteraction.locateElement(errorMessage_text).getText();
    }

}
