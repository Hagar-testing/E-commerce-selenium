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

    @FindBy(tagName = "ngx-spinner")
    WebElement spinnerLocator;

    @FindBy(id = "res")
    WebElement productsCountTextElement;

    @FindBy(xpath = "//input[@placeholder='search']")
    WebElement searchInput;

    By toastLocator = By.id("toast-container");


    public List<WebElement> getFilteredProductsByProductName(String productName){
        Stream<WebElement> filteredProducts = productsList.stream().filter(
                a -> a.getText().contains(productName)
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



    public int getAllProductsCount() {
        String text = productsCountTextElement.getText();
        Matcher matcher = Pattern.compile("\\d+").matcher(text);

        return matcher.find() ? Integer.parseInt(matcher.group()) : 0;

    }

    public int getProductsListSize(){
        return productsList.stream().toList().size();
    }

    public WebElement getFirstProductFromProductsList(){
        return getProductsListSize() > 0 ? productsList.get(0) : null;
    }

    public ProductsPage addProductToList(WebElement product) {
        product.findElement(By.xpath(".//following-sibling::button[contains(., 'Cart')]")).click();
        return this;
    }

    public ProductsPage searchFor(String searchText) throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // waitUtils.waitForElementToBeClickable(By.xpath("(//input[@placeholder='search'])[1]"));
        js.executeScript("arguments[0].value = arguments[1];", searchInput, "your search query");

        searchInput.sendKeys(searchText);
        // Simulate pressing the "Enter" key
        //searchInput.sendKeys(Keys.ENTER);

        return this;
    }

    public Boolean checkIfAllProductsContains(String searchString){
        return productsList.size() < 1 || productsList
                .stream()
                .map(WebElement::getText)
                .anyMatch(text -> text.toLowerCase().contains(searchString));

    }

    public Header addFirstProductToCart(){
        addProductToList(getFirstProductFromProductsList());
        return new Header(driver);

    }

}
