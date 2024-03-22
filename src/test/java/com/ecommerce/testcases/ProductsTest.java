package com.ecommerce.testcases;

import com.ecommerce.base.BaseTest;
import com.ecommerce.pages.LoginPage;
import com.ecommerce.pages.ProductsPage;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.ecommerce.utils.ConfigUtils.getEmail;
import static com.ecommerce.utils.ConfigUtils.getPassword;

@Feature("Products Feature")
public class ProductsTest extends BaseTest {

    @Story("Verify Products List")
    @Description("Test if the shown products text equals the products list size")
    @Test(description = "Test if shown products text equals products list size")
    public void testIfShownProductsTextEqualsToProductsListSize() {

        ProductsPage productsPage = new LoginPage(getDriver()).load().login(getEmail(), getPassword());

        int productsListSize = productsPage.getProductsListSize();
        int count = productsPage.getAllProductsCount();

        Assert.assertEquals(count, productsListSize);
    }

    @Story("Add Product to Cart")
    @Description("Test if a product is added successfully to the cart")
    @Test(description = "Test if product is added successfully to the cart")
    public void testIfProductIsAddedSuccessfullyToCart() throws InterruptedException {
        Integer cartItemsCount = new LoginPage(getDriver())
                .load()
                .login(getEmail(), getPassword())
                .addFirstProductToCart()
                .goToHeader()
                .getCartItemsCount();

        Assert.assertEquals(cartItemsCount, Integer.valueOf(1));
    }
}
