package com.ecommerce.pages;

import com.ecommerce.base.BasePage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Header extends BasePage {
    @FindBy(xpath = "//button[@routerlink='/dashboard/cart']/label")
    WebElement productsCountCart;

    public Header(WebDriver driver) {
        super(driver);
    }


    public CartPage goToCartPage(){
        javascriptExecutorUtils.executeJavaScriptClick(productsCountCart);
        return new CartPage(driver);
    }

    public int getCartItemsCount() throws InterruptedException {
        Thread.sleep(1000);
        return Integer.parseInt(productsCountCart.getText());
    }


}
