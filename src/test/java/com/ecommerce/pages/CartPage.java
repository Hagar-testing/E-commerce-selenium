package com.ecommerce.pages;

import com.ecommerce.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static com.ecommerce.locators.CartPageLocators.CHECKOUT_BUTTON_XPATH;
import static com.ecommerce.locators.CartPageLocators.PRODUCT_TITLE_XPATH;

public class CartPage extends BasePage {


    public CartPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = CHECKOUT_BUTTON_XPATH)
    WebElement checkoutBtn;


    public CheckoutPage clickOnCheckoutButton() {
        javascriptExecutorUtils.executeJavaScriptClick(checkoutBtn);
        return new CheckoutPage(driver);
    }



    public Boolean isCartContainsProducts(){
        List<WebElement> productsInCart = driver.findElements(By.xpath(PRODUCT_TITLE_XPATH));
        return productsInCart.size() > 0;
    }
}
