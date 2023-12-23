package com.ecommerce.testcases;

import com.ecommerce.base.BaseTest;
import com.ecommerce.pages.LandingPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.ecommerce.utils.ConfigUtils.getEmail;
import static com.ecommerce.utils.ConfigUtils.getPassword;

public class LandingTest extends BaseTest {

    @Test
    public void login(){
        String expectedMessage = "Login Successfully";

        LandingPage landingPage = new LandingPage(getDriver());
        String isHomeTextIsDisplayed = landingPage
                .load()
                .login(getEmail(), getPassword())
                .getToastMessageText();


        Assert.assertEquals(isHomeTextIsDisplayed, expectedMessage);
    }
}
