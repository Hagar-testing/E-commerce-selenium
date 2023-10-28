package org.hager.pages;

import org.hager.utils.webdriver.JavascriptExecutorUtils;
import org.hager.utils.webdriver.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Header {

    private final WebDriver driver;
    private final JavascriptExecutorUtils javascriptExecutorUtils;
    private final WaitUtils waitUtils ;

    @FindBy(xpath = "//button[@routerlink='/dashboard/cart']/label")
    WebElement itemsCountInCart;

    public Header(WebDriver driver, WaitUtils waitUtils,JavascriptExecutorUtils javascriptExecutorUtils) {
        this.driver = driver;
        this.javascriptExecutorUtils = javascriptExecutorUtils;
        this.waitUtils = waitUtils;
        PageFactory.initElements(driver,this);

    }


    public CartPage goToCartPage(){
        itemsCountInCart.click();
        return new CartPage(driver,waitUtils,javascriptExecutorUtils);
    }


}
