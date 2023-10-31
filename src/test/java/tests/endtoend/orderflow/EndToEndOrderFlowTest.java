package tests.endtoend.orderflow;

import base.BaseTest;
import org.hager.pages.*;
import org.hager.utils.webdriver.JavascriptExecutorUtils;
import org.hager.utils.webdriver.WaitUtils;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class EndToEndOrderFlowTest extends BaseTest {
    private static final String USER_EMAIL = "hajer.ibr@gmail.com";
    private static final String USER_PASSWORD = "Hajer_95";
    private WaitUtils waitUtils;
    private JavascriptExecutorUtils jsExecutorUtils;
    private ProductsPage productsPage;
    private LandingPage landingPage;

    private CartPage cartPage;

    private List<WebElement> filteredProducts;
    @BeforeMethod
    public void setUp() {
        waitUtils = new WaitUtils(driver);
        jsExecutorUtils = new JavascriptExecutorUtils(driver);
    }

    @Test
    public void login() {
        landingPage = new LandingPage(driver, waitUtils);
        landingPage.open();
        landingPage.login(USER_EMAIL, USER_PASSWORD);
    }

    @Test(dependsOnMethods = "login")
    public void navigateToProductsPage() {
        productsPage = landingPage.transitionToProductsPage();
    }

    @Test(dependsOnMethods = "navigateToProductsPage")
    public void searchProductsAndAddProductsToCart() {
        String productNameToSearch = "zara coat 3";
        filteredProducts = productsPage.getFilteredProductsByProductName(productNameToSearch);
        productsPage.addProductsToCart(filteredProducts);
        Assert.assertEquals("Product Added To Cart", productsPage.getToastMessageText());
    }

    @Test(dependsOnMethods = "searchProductsAndAddProductsToCart")
    public void proceedToCartAndVerifyContents() {
        Header header = new Header(driver, waitUtils, jsExecutorUtils);
        List<String> filteredProductsNamesList = productsPage.getFilteredProductsNames(filteredProducts);
        cartPage = header.goToCartPage();
        List<String> productsInCart = cartPage.getProductsListInCart();
        Assert.assertEquals(filteredProductsNamesList, productsInCart);
    }

    @Test(dependsOnMethods = "proceedToCartAndVerifyContents")
    public void completeOrder() {
        cartPage.clickOnCheckoutButton();
        CheckoutPage checkoutPage = cartPage.goToCheckoutPage();
        checkoutPage.selectCountry("Egy");
        checkoutPage.selectPlaceOrderButton();
    }
}
