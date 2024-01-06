package com.ecommerce.testcases;

import com.ecommerce.api.LoginAPI;
import com.ecommerce.api.RegisterAPI;
import com.ecommerce.base.BaseTest;
import com.ecommerce.pages.ProductsPage;
import org.testng.annotations.Test;

public class ProductsTest extends BaseTest {


    @Test
    public void test(){
        RegisterAPI registerAPI = new RegisterAPI();
        registerAPI.register();

        LoginAPI loginAPI = new LoginAPI();
        loginAPI.login(registerAPI.getEmail(),registerAPI.getPassword());

        ProductsPage productsPage = new ProductsPage(getDriver());
        productsPage.load().getAllProductsCount();


    }


}
