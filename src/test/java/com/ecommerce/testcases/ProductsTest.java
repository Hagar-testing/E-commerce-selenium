package com.ecommerce.testcases;

import com.ecommerce.base.BaseTest;
import com.ecommerce.pages.Header;
import com.ecommerce.pages.LandingPage;
import com.ecommerce.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.ecommerce.utils.ConfigUtils.getEmail;
import static com.ecommerce.utils.ConfigUtils.getPassword;

public class ProductsTest extends BaseTest {


    @Test
    public void testIfShownProductsTextEqualsToProductsListSize(){

        ProductsPage productsPage = new LandingPage(getDriver()).load().login(getEmail(), getPassword());

        int productsListSize = productsPage.getProductsListSize();
        int count = productsPage.getAllProductsCount();

        Assert.assertEquals(count,productsListSize);

    }


    @Test
    public void testIfProductIsAddedSuccessfullyToCart() throws InterruptedException {
        Integer cartItemsCount = new LandingPage(getDriver())
                .load()
                .login(getEmail(), getPassword())
                .addFirstProductToCart()
                .getCartItemsCount();

        Assert.assertEquals(cartItemsCount, 1);
    }



}
