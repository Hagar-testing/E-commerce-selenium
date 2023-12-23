package com.ecommerce.pages;

import com.ecommerce.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Header extends BasePage {
    @FindBy(xpath = "//button[@routerlink='/dashboard/cart']/label")
    WebElement itemsCountInCart;

    public Header(WebDriver driver) {
        super(driver);
    }


    public CartPage goToCartPage(){
        itemsCountInCart.click();
        return new CartPage(driver);
    }


}
