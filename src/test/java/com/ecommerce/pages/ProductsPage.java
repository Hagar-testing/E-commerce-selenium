package com.ecommerce.pages;

import com.ecommerce.base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static com.ecommerce.locators.ProductsLocators.*;
import static com.ecommerce.utils.RegexUtils.DIGITS_PATTERN;

public class ProductsPage extends BasePage {


    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = PRODUCTS_LIST_XPATH)
    List<WebElement> productsList;

    @FindBy(id = PRODUCTS_COUNT_TEXT_ELEMENT_ID)
    WebElement productsCountTextElement;

    By toastLocator = By.id(TOAST_CONTAINER_ID);


    @Step
    public String getToastMessageText() {
        WebElement toastMessageElement = waitUtils.waitForPresenceOfElement(toastLocator);
        return toastMessageElement.getText();
    }

    @Step
    public int getAllProductsCount() {
        String text = productsCountTextElement.getText();
        Matcher matcher = Pattern.compile(DIGITS_PATTERN).matcher(text);

        return matcher.find() ? Integer.parseInt(matcher.group()) : 0;

    }
    @Step
    public int getProductsListSize() {
        return productsList.stream().toList().size();
    }

    @Step
    public WebElement getFirstProductFromProductsList() {
        return getProductsListSize() > 0 ? productsList.get(0) : null;
    }
    @Step
    public ProductsPage addProductToList(WebElement product) {
        product.findElement(By.xpath(ADD_TO_CART_BUTTON_XPATH)).click();
        return this;
    }

    @Step
    public Header addFirstProductToCart() {
        addProductToList(getFirstProductFromProductsList());
        return new Header(driver);

    }
    @Step
    public Boolean isProductsListDisplayed(){
        return  productsCountTextElement.isDisplayed();
    }

}
