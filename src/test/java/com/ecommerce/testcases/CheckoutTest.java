package com.ecommerce.testcases;

import com.ecommerce.base.BaseTest;
import com.ecommerce.data.reader.DataReader;
import com.ecommerce.data.reader.TestDataProviders;
import com.ecommerce.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;

import static com.ecommerce.constants.DataFilesPathConstants.CHECKOUT_DATA_FILE_PATH;
import static com.ecommerce.constants.JsonKeysConstants.COUNTRY_NAME;
import static com.ecommerce.utils.ConfigUtils.getEmail;
import static com.ecommerce.utils.ConfigUtils.getPassword;

public class CheckoutTest extends BaseTest {

    @Test(dataProvider = "checkoutData", dataProviderClass = TestDataProviders.class)
    public void CheckIfCheckoutProcessWorksSuccessfully(HashMap<String, String> data ) {
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

