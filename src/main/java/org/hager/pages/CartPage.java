package org.hager.pages;

import org.hager.utils.webdriver.JavascriptExecutorUtils;
import org.hager.utils.webdriver.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage {

    private final WebDriver driver;

    private final JavascriptExecutorUtils javascriptExecutorUtils;

    private final WaitUtils waitUtils;

    public CartPage(WebDriver driver,  WaitUtils waitUtils,JavascriptExecutorUtils javascriptExecutorUtils) {
        this.driver = driver;
        this.javascriptExecutorUtils = javascriptExecutorUtils;
        this.waitUtils = waitUtils;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//button[text()= 'Checkout']")
    WebElement checkoutBtn;

    public List<String> getProductsListInCart(){
        List<WebElement> productsInCart = driver.findElements(By.xpath("//div[@class=\"infoWrap\"]//h3"));
        return productsInCart.stream().map(WebElement::getText).toList();
    }

    public void clickOnCheckoutButton(){
        javascriptExecutorUtils.executeJavaScriptClick(checkoutBtn);
    }

    public CheckoutPage goToCheckoutPage(){
        return new CheckoutPage(driver,waitUtils,javascriptExecutorUtils);
    }
}
