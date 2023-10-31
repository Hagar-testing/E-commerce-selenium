package tests.endtoend.orderflow;

import base.BaseTest;
import org.hager.pages.*;
import org.hager.utils.webdriver.JavascriptExecutorUtils;
import org.hager.utils.webdriver.WaitUtils;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.List;

public class EndToEndOrderFlowTest extends BaseTest {
    private WaitUtils waitUtils;
    private JavascriptExecutorUtils javascriptExecutorUtils;
    private ProductsPage productsPage;
    private LandingPage landingPage;
    private CartPage cartPage;
    private List<WebElement> filteredProducts;

    @BeforeMethod
    public void setUp() {
        waitUtils = new WaitUtils(driver);
        javascriptExecutorUtils = new JavascriptExecutorUtils(driver);
    }

    @Test(dataProvider = "loginData")
    public void verifyLogin(String userEmail, String userPassword) {
        landingPage = new LandingPage(driver, waitUtils);
        landingPage.open();
        landingPage.login(userEmail, userPassword);
    }

    @Test(dependsOnMethods = "verifyLogin")
    public void navigateToProductsPage() {
        productsPage = landingPage.transitionToProductsPage();
    }

    @Test(dependsOnMethods = "navigateToProductsPage", dataProvider = "productNameData")
    public void searchProductsAndAddToCart(String productNameToSearch) {
        filteredProducts = productsPage.getFilteredProductsByProductName(productNameToSearch);
        productsPage.addProductsToCart(filteredProducts);
        String expectedMessage = "Product Added To Cart";
        String actualMessage = productsPage.getToastMessageText();
        Assert.assertEquals(actualMessage, expectedMessage, "Toast message does not match the expected value");
    }

    @Test(dependsOnMethods = "searchProductsAndAddToCart")
    public void proceedToCartAndVerifyContents() {
        Header header = new Header(driver, waitUtils, javascriptExecutorUtils);
        List<String> filteredProductsNamesList = productsPage.getFilteredProductsNames(filteredProducts);
        cartPage = header.goToCartPage();
        List<String> productsInCart = cartPage.getProductsListInCart();
        Assert.assertEquals(filteredProductsNamesList, productsInCart, "Cart contents do not match the expected products");
    }

    @Test(dependsOnMethods = "proceedToCartAndVerifyContents", dataProvider = "countriesData")
    public void completeOrder(String countryName) {
        cartPage.clickOnCheckoutButton();
        CheckoutPage checkoutPage = cartPage.goToCheckoutPage();
        checkoutPage.selectCountry(countryName);
        checkoutPage.selectPlaceOrderButton();
    }

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return new Object[][] {
                { "hajer.ibr@gmail.com", "Hajer_95"},
        };
    }

    @DataProvider(name = "productNameData")
    public Object[][] productNameData() {
        return new Object[][] {
                { "zara coat 3"},
        };
    }

    @DataProvider(name = "countriesData")
    public Object[][] countriesData() {
        return new Object[][] {
                { "Egy"},
        };
    }
}
