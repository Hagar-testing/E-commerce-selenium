package tests.endtoend.orderflow;

import base.BaseTest;
import org.hager.pages.*;
import org.hager.utils.webdriver.JavascriptExecutorUtils;
import org.hager.utils.webdriver.WaitUtils;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class EndToEndOrderFlowTest extends BaseTest {

    @Test
    public void submitOrder() {
        // Initialize utility classes
        WaitUtils waitUtils = new WaitUtils(driver);
        JavascriptExecutorUtils javascriptExecutorUtils = new JavascriptExecutorUtils(driver);

        // Initialize page objects
        LandingPage landingPage = new LandingPage(driver, waitUtils);
        ProductsPage productsPage = new ProductsPage(driver, waitUtils);
        Header header = new Header(driver, waitUtils, javascriptExecutorUtils);
        CartPage cartPage = new CartPage(driver, waitUtils,javascriptExecutorUtils);
        CheckoutPage checkoutPage = new CheckoutPage(driver, waitUtils,javascriptExecutorUtils);

        // Test Steps
        landingPage.open();
        landingPage.login("hajer.ibr@gmail.com", "Hajer_95");

        // Navigate to the products page
        productsPage = landingPage.transitionToProductsPage();



        // Search for and add products to the cart
        String productNameToSearch = "zara coat 3";
        List<WebElement> filteredProducts = productsPage.getFilteredProductsByProductName(productNameToSearch);
        List<String> filteredProductsNamesList = productsPage.getFilteredProductsNames(filteredProducts);
        productsPage.addProductsToCart(filteredProducts);
        // Verify successful product addition
        Assert.assertEquals("Product Added To Cart", productsPage.getToastMessageText());

        // Proceed to cart and verify the cart contents
        header.goToCartPage();
        List<String> productsInCart = cartPage.getProductsListInCart();
        Assert.assertEquals(filteredProductsNamesList, productsInCart);

        // Proceed to checkout and complete the order
        cartPage.clickOnCheckoutButton();
        checkoutPage.selectCountry("Egy");
        checkoutPage.selectPlaceOrderButton();
    }
}

