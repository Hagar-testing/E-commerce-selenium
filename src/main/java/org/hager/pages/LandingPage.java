package org.hager.pages;

import org.hager.config.ConfigurationManager;
import org.hager.utils.webdriver.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {
    private final WebDriver driver;
    private final WaitUtils waitUtils;

    @FindBy(id = "userEmail")
    WebElement mail;
    @FindBy(id = "userPassword")
    WebElement password;
    @FindBy(id = "login")
    WebElement loginBtn;
    @FindBy(xpath = "//div[@class='ng-tns-c4-0 toast-message ng-star-inserted']")
    WebElement errorMessage;

    public LandingPage(WebDriver driver, WaitUtils waitUtils) {
        this.driver = driver;
        this.waitUtils = waitUtils;
        PageFactory.initElements(driver,this);
    }

    public void login(String email, String pass){
        mail.sendKeys(email);
        password.sendKeys(pass);
        loginBtn.click();
    }
    public ProductsPage transitionToProductsPage(){
        return new ProductsPage(driver,waitUtils);
    }

    public void open(){
        driver.get(ConfigurationManager.getBaseUrl() + "client/");
    }

    public String getErrorMessage(){
        waitUtils.waitForVisibilityOfElement(errorMessage);
        return errorMessage.getText();
    }

}
