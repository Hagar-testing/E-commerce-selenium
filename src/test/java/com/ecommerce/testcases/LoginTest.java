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

@Feature("Login Feature")
public class LoginTest extends BaseTest {

    @Story("Standard Login")
    @Description("Verify if the login process displays the products list")
    @Test(description = "Test the login functionality and check if products are displayed")
    public void login() {
        Boolean isProductsDisplayed = new LoginPage(getDriver())
                .load()
                .login(getEmail(), getPassword())
                .isProductsListDisplayed();

        Assert.assertTrue(isProductsDisplayed);
    }
}
