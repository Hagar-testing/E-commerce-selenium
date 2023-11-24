package tests.endtoend.orderflow;

import utils.base.BaseTest;
import data.reader.DataReader;
import org.hager.pages.*;
import org.hager.utils.webdriver.JavascriptExecutorUtils;
import org.hager.utils.webdriver.WaitUtils;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;


public class EndToEndOrderFlowTest extends BaseTest {
    private WaitUtils waitUtils;
    private JavascriptExecutorUtils javascriptExecutorUtils;
    private ProductsPage productsPage;
    private LandingPage landingPage;
    private CartPage cartPage;
    private List<WebElement> filteredProducts;
    DataReader reader = new DataReader();
    private static final String DATA_FILE_PATH = "OrderFlowData.json";

    @BeforeMethod
    public void setUp() {
        waitUtils = new WaitUtils(driver);
        javascriptExecutorUtils = new JavascriptExecutorUtils(driver);
    }

    @Test(dataProvider = "loginData")
    public void testOrderFlow(HashMap<String, String> data ){
        verifyLogin(data.get("userEmail"), data.get("userPassword"));
        navigateToProductsPage();
        searchProductsAndAddToCart(data.get("productNameToSearch"));
        verifyDisplayedToastMsgAfterAddingToCart();
        proceedToCartAndVerifyContents();
        completeOrder(data.get("countryName"));
    }
    public void verifyLogin(String userEmail, String userPassword) {
        landingPage = new LandingPage(driver, waitUtils);
        landingPage.open();
        landingPage.login(userEmail, userPassword);
    }

    public void navigateToProductsPage() {
        productsPage = landingPage.transitionToProductsPage();
    }

    public void searchProductsAndAddToCart(String productNameToSearch) {
        filteredProducts = productsPage.getFilteredProductsByProductName(productNameToSearch);
        productsPage.addProductsToCart(filteredProducts);
    }


    public void verifyDisplayedToastMsgAfterAddingToCart(){
        String expectedMessage = "Product Added To Cart";
        String actualMessage = productsPage.getToastMessageText();
        Assert.assertEquals(actualMessage, expectedMessage, "Toast message does not match the expected value");
    }

    public void proceedToCartAndVerifyContents() {
        Header header = new Header(driver, waitUtils, javascriptExecutorUtils);
        List<String> filteredProductsNamesList = productsPage.getFilteredProductsNames(filteredProducts);
        cartPage = header.goToCartPage();
        List<String> productsInCart = cartPage.getProductsListInCart();
        Assert.assertEquals(filteredProductsNamesList, productsInCart, "Cart contents do not match the expected products");
    }

    public void completeOrder(String countryName) {
        cartPage.clickOnCheckoutButton();
        CheckoutPage checkoutPage = cartPage.goToCheckoutPage();
        checkoutPage.selectCountry(countryName);
        checkoutPage.selectPlaceOrderButton();
    }

    @DataProvider(name = "loginData")
    public Object[][] loginData() throws IOException {
        List<HashMap<String, String>> map = reader.convertJsonDataToMap(DATA_FILE_PATH);
        return new Object[][] {
                {map.get(0)}
        };
    }

}
