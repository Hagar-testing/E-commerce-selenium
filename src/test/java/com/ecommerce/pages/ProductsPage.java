package com.ecommerce.pages;

import com.ecommerce.base.BasePage;
import com.ecommerce.utils.ConfigUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static com.ecommerce.constants.UrlPathConstants.DASHBOARD_PATH;

public class ProductsPage extends BasePage {


    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@class='card']//h5")
    List<WebElement> productsList;

    @FindBy(tagName = "ngx-spinner")
    WebElement spinnerLocator;

    @FindBy(css = "#res")
    WebElement productsCountTextElement;
    By toastLocator = By.id("toast-container");


    public ProductsPage load(){
        driver.get(ConfigUtils.getBaseUrl() + DASHBOARD_PATH);
        return this;
    }

    public List<WebElement> getFilteredProductsByProductName(String productName){
        Stream<WebElement> filteredProducts = productsList.stream().filter(
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

    public void getAllProductsCount(){
        String text = productsCountTextElement.getText();

        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            int count = Integer.parseInt(matcher.group());
            System.out.println("The count is: " + count);
        } else {
            System.out.println("Count not found in the text.");
        }
    }



}
