package com.ecommerce.testcases;

import com.ecommerce.base.BaseTest;
import com.ecommerce.pages.LandingPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.ecommerce.utils.ConfigUtils.getEmail;
import static com.ecommerce.utils.ConfigUtils.getPassword;

public class CheckoutTest extends BaseTest {

    @Test
    public void CheckIfCheckoutProcessWorksSuccessfully() {
        Boolean message = new LandingPage(getDriver())
                .load()
                .login(getEmail(), getPassword())
                .addFirstProductToCart()
                .goToCartPage()
                .clickOnCheckoutButton()
                .selectCountry("Egy")
                .clickPlaceOrderButton()
                .isThankYouForPlacingOrderDisplayed();

        Assert.assertTrue(message);


    }
}

