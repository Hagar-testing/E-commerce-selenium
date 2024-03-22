package com.ecommerce.pages;

import com.ecommerce.base.BasePage;
import com.ecommerce.enums.LocatorType;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static com.ecommerce.locators.CartPageLocators.CHECKOUT_BUTTON_XPATH;
import static com.ecommerce.locators.CartPageLocators.PRODUCT_TITLE_XPATH;

public class CartPage extends BasePage {

    @FindBy(xpath = CHECKOUT_BUTTON_XPATH)
    WebElement checkout_button;
    private final By products_list = By.xpath(PRODUCT_TITLE_XPATH);

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Step
    public CheckoutPage clickOnCheckoutButton() {
        elementInteraction.javascriptClick(checkout_button, LocatorType.WITH_WAIT);
        return new CheckoutPage(driver);
    }

    @Step
    public int getNumberOfProductsInCart(){
        List<WebElement> productsInCart = driver.findElements(products_list);
        return productsInCart.size() ;
    }
}
