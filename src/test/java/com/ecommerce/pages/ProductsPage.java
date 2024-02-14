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

    private final By products_list = By.xpath(PRODUCTS_LIST_XPATH);
    private final By productsCount_text = By.id(PRODUCTS_COUNT_TEXT_ELEMENT_ID);
    private final By toast_text = By.id(TOAST_CONTAINER_ID);

    private final By addToCart_button = By.xpath(ADD_TO_CART_BUTTON_XPATH);

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

//    @Step
//    public String getToastMessageText() {
//        WebElement toastMessageElement = waitUtils.waitForPresenceOfElement(toastLocator);
//        return toastMessageElement.getText();
//    }

    @Step
    public int getAllProductsCount() {
        Matcher matcher = Pattern.compile(DIGITS_PATTERN)
                .matcher(elementInteraction.locateElement(productsCount_text).getText());

        return matcher.find() ? Integer.parseInt(matcher.group()) : 0;

    }
    @Step
    public int getProductsListSize() {
        return elementListInteraction.locateElementList(products_list).stream().toList().size();
    }

    @Step
    public WebElement getFirstProductFromProductsList() {
        return getProductsListSize() > 0 ? elementListInteraction.locateElementList(products_list).get(0) : null;
    }
    @Step
    public ProductsPage addProductToList(WebElement product) {
        product.findElement(addToCart_button).click();
        return this;
    }

    @Step
    public Header addFirstProductToCart() {
        addProductToList(getFirstProductFromProductsList());
        return new Header(driver);

    }
    @Step
    public Boolean isProductsListDisplayed(){
        return  elementInteraction.locateElement(productsCount_text).isDisplayed();
    }

}
