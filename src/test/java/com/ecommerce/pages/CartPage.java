package com.ecommerce.pages;

import com.ecommerce.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartPage extends BasePage {


    public CartPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//button[text()= 'Checkout']")
    WebElement checkoutBtn;

    public List<String> getProductsListInCart() {
        List<WebElement> productsInCart = driver.findElements(By.xpath("//div[@class=\"infoWrap\"]//h3"));
        return productsInCart.stream().map(WebElement::getText).toList();
    }

    public void clickOnCheckoutButton() {
        javascriptExecutorUtils.executeJavaScriptClick(checkoutBtn);
    }

    public CheckoutPage goToCheckoutPage() {
        return new CheckoutPage(driver);
    }

    public Boolean isCartContainsProducts(){
        List<WebElement> productsInCart = driver.findElements(By.xpath("//div[@class=\"infoWrap\"]//h3"));
        return productsInCart.size() > 0;
    }
}
