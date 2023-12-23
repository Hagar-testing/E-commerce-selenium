package com.ecommerce.pages;

import com.ecommerce.base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Stream;

public class ProductsPage extends BasePage {


    public ProductsPage(WebDriver driver) {
        super(driver);
    }


    @FindBy(xpath = "//div[@class='card']//h5")
    List<WebElement> products2;

    @FindBy(tagName = "ngx-spinner")
    WebElement spinnerLocator;

    By toastLocator = By.id("toast-container");

    public List<WebElement> getFilteredProductsByProductName(String productName){
        Stream<WebElement> filteredProducts = products2.stream().filter(
                a -> a.getText().equalsIgnoreCase(productName)
        );
        return filteredProducts.toList();
    }

    public void addProductsToCart(List<WebElement> filteredProductsList){
        filteredProductsList.forEach(
                a -> a.findElement(By.xpath("//following-sibling::button[contains(., 'Cart')]")).click()
        );
        waitUtils.waitForVisibilityOfElement(spinnerLocator);
        waitUtils.waitForInvisibilityOfElement(spinnerLocator);

    }

    public List<String> getFilteredProductsNames(List<WebElement> filteredProductsList){
        return filteredProductsList.stream().map(WebElement::getText).toList();
    }


    public String getToastMessageText() {
        WebElement toastMessageElement = waitUtils.waitForPresenceOfElement(toastLocator);
        return toastMessageElement.getText();
    }



}
