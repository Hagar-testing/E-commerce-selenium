package com.ecommerce.testcases;

import com.ecommerce.base.BaseTest;
import com.ecommerce.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.ecommerce.utils.ConfigUtils.getEmail;
import static com.ecommerce.utils.ConfigUtils.getPassword;

public class LoginTest extends BaseTest {

    @Test
    public void login(){
        String expectedMessage = "Login Successfully";

        String isHomeTextIsDisplayed = new LoginPage(getDriver())
                .load()
                .login(getEmail(), getPassword())
                .getToastMessageText();

        Assert.assertEquals(isHomeTextIsDisplayed, expectedMessage);
    }
}
