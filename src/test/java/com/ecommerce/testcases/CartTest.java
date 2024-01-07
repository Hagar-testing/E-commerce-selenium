package com.ecommerce.testcases;

import com.ecommerce.base.BaseTest;
import com.ecommerce.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import static com.ecommerce.utils.ConfigUtils.getEmail;
import static com.ecommerce.utils.ConfigUtils.getPassword;

public class CartTest extends BaseTest {

    @Test
    public void CheckIfCartScreenHasProductAfterClickOnAddToCart() {
        Boolean cartHasProducts = new LoginPage(getDriver())
                .load()
                .login(getEmail(), getPassword())
                .addFirstProductToCart()
                .goToCartPage().
                isCartContainsProducts();

        Assert.assertTrue(cartHasProducts);


    }
}
