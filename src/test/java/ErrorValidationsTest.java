import org.hager.pages.LandingPage;
import org.hager.utils.webdriver.WaitUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ErrorValidationsTest extends BaseTest {

    private LandingPage landingPage ;

    @BeforeTest
    public void setup(){
        super.setup();
        WaitUtils waitUtils = new WaitUtils(driver);
        landingPage = new LandingPage(driver, waitUtils);
    }

    @Test
    public void loginErrorMessageValidations(){

        landingPage.open();
        landingPage.login("hajer.ibr@gml.com", "H_95");
        String loginErrorMessage = landingPage.getErrorMessage();
        Assert.assertEquals(loginErrorMessage,"Incorrect email or password.");
    }


//    @Test(dependsOnMethods = "loginErrorMessageValidations")
//    public void addToCartErrorMsgValidations(){
//        landingPage.open();
//        landingPage.login("hajer.ibr@gmail.com", "Hajer_95");
//
//        ProductsPage productsPage =  landingPage.transitionToProductsPage();
//        List<WebElement> filteredProducts = productsPage.getFilteredProductsByProductName("zara coat 3");
//        productsPage.addProductsToCart(filteredProducts);
//        String toastMessageText = productsPage.getToastMessageText();
//        Assert.assertEquals(toastMessageText,"Product Added To Cart");
//    }

}
