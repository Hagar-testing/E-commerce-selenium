package com.ecommerce.testcases;

import com.ecommerce.base.BaseTest;
import com.ecommerce.data.reader.DataReader;
import com.ecommerce.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static com.ecommerce.constants.DataFilesPathConstants.DATA_FILE_PATH;
import static com.ecommerce.utils.ConfigUtils.getEmail;
import static com.ecommerce.utils.ConfigUtils.getPassword;

public class CheckoutTest extends BaseTest {

    @Test(dataProvider = "checkoutData" )
    public void CheckIfCheckoutProcessWorksSuccessfully(String countryName) {
        Boolean message = new LoginPage(getDriver())
                .load()
                .login(getEmail(), getPassword())
                .addFirstProductToCart()
                .goToCartPage()
                .clickOnCheckoutButton()
                .selectCountry(countryName)
                .clickPlaceOrderButton()
                .isThankYouForPlacingOrderDisplayed();

        Assert.assertTrue(message);


    }


    @DataProvider(name = "checkoutData")
    public Object[][] checkoutData() throws IOException {
        DataReader reader = new DataReader();
        List<HashMap<String, String>> map;
        map = reader.convertJsonDataToMap(DATA_FILE_PATH);
        return new Object[][] {{map.get(0)}
        };
    }
}

