import org.hager.pages.*;
import org.hager.utils.webdriver.JavascriptExecutorUtils;
import org.hager.utils.webdriver.WaitUtils;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class SubmitOrderTest extends BaseTest {


    @Test
    public void test(){
        WaitUtils waitUtils = new WaitUtils(driver);
        JavascriptExecutorUtils javascriptExecutorUtils = new JavascriptExecutorUtils(driver);
        Header header = new Header(driver,waitUtils,javascriptExecutorUtils);

        LandingPage landingPage = new LandingPage(driver,waitUtils);
        landingPage.open();
        landingPage.login("hajer.ibr@gmail.com", "Hajer_95");

        ProductsPage productsPage =  landingPage.transitionToProductsPage();
        List<WebElement> filteredProducts = productsPage.getFilteredProductsByProductName("zara coat 3");
        List<String> filteredProductsNamesList = productsPage.getFilteredProductsNames(filteredProducts);
        productsPage.addProductsToCart(filteredProducts);
        String toastMessageText = productsPage.getToastMessageText();
        Assert.assertEquals(toastMessageText,"Product Added To Cart");

        CartPage cartPage = header.goToCartPage();
        List<String> productsInCart = cartPage.getProductsListInCart();
        Assert.assertEquals(filteredProductsNamesList,productsInCart);
        cartPage.clickOnCheckoutButton();

        CheckoutPage checkoutPage = cartPage.goToCheckoutPage();
        checkoutPage.selectCountry("Egy");
        checkoutPage.selectPlaceOrderButton();
    }


}
