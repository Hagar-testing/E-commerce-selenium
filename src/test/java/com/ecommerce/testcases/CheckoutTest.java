package com.ecommerce.testcases;

import com.ecommerce.base.BaseTest;
import com.ecommerce.data.reader.DataReader;
import com.ecommerce.data.reader.TestDataProviders;
import com.ecommerce.pages.LoginPage;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;

import static com.ecommerce.constants.DataFilesPathConstants.CHECKOUT_DATA_FILE_PATH;
import static com.ecommerce.constants.JsonKeysConstants.COUNTRY_NAME;
import static com.ecommerce.utils.ConfigUtils.getEmail;
import static com.ecommerce.utils.ConfigUtils.getPassword;

@Feature("Checkout Process Feature")
public class CheckoutTest extends BaseTest {

    @Story("Complete Checkout Process")
    @Description("Verify if the checkout process works successfully with provided data")
    @Test(dataProvider = "checkoutData", dataProviderClass = TestDataProviders.class, description = "Test the successful completion of the checkout process")
    public void CheckIfCheckoutProcessWorksSuccessfully(HashMap<String, String> data) {
        Boolean message = new LoginPage(getDriver())
                .load()
                .login(getEmail(), getPassword())
                .addFirstProductToCart()
                .goToCartPage()
                .clickOnCheckoutButton()
                .selectCountry(data.get(COUNTRY_NAME))
                .clickPlaceOrderButton()
                .isThankYouForPlacingOrderDisplayed();

        Assert.assertTrue(message);
    }
}


