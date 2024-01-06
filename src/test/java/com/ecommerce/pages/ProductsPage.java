package com.ecommerce.pages;

import com.ecommerce.base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class ProductsPage extends BasePage {


    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@class='card']//h5")
    List<WebElement> productsList;


    @FindBy(id = "res")
    WebElement productsCountTextElement;

    @FindBy(xpath = "//input[@placeholder='search']")
    WebElement searchInput;

    By toastLocator = By.id("toast-container");




    public String getToastMessageText() {
        WebElement toastMessageElement = waitUtils.waitForPresenceOfElement(toastLocator);
        return toastMessageElement.getText();
    }


    public int getAllProductsCount() {
        String text = productsCountTextElement.getText();
        Matcher matcher = Pattern.compile("\\d+").matcher(text);

        return matcher.find() ? Integer.parseInt(matcher.group()) : 0;

    }

    public int getProductsListSize() {
        return productsList.stream().toList().size();
    }

    public WebElement getFirstProductFromProductsList() {
        return getProductsListSize() > 0 ? productsList.get(0) : null;
    }

    public ProductsPage addProductToList(WebElement product) {
        product.findElement(By.xpath(".//following-sibling::button[contains(., 'Cart')]")).click();
        return this;
    }


    public Header addFirstProductToCart() {
        addProductToList(getFirstProductFromProductsList());
        return new Header(driver);

    }

}
