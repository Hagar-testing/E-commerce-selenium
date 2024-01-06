package com.ecommerce.base;

import com.ecommerce.utils.JavascriptExecutorUtils;
import com.ecommerce.utils.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

    protected final JavascriptExecutorUtils javascriptExecutorUtils;

    protected final WaitUtils waitUtils;

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
        this.javascriptExecutorUtils = new JavascriptExecutorUtils(driver);
        PageFactory.initElements(driver, this);
    }
}
