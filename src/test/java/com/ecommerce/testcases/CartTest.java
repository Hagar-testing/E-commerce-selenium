package com.ecommerce.testcases;

import com.ecommerce.base.BaseTest;
import com.ecommerce.pages.LoginPage;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;
import static com.ecommerce.utils.ConfigUtils.getEmail;
import static com.ecommerce.utils.ConfigUtils.getPassword;

@Feature("Shopping Cart Feature")
public class CartTest extends BaseTest {

    @Story("Add to Cart")
    @Description("Verify if the cart screen displays products after clicking on 'Add to Cart'")
    @Test(description = "Test checking if the cart screen has products after clicking on 'Add to Cart'")
    public void CheckIfCartScreenHasProductAfterClickOnAddToCart() {
        Boolean cartHasProducts = new LoginPage(getDriver())
                .load()
                .login(getEmail(), getPassword())
                .addFirstProductToCart()
                .goToHeader()
                .goToCartPage()
                .isCartContainsProducts();

        Assert.assertTrue(cartHasProducts);
    }
}
