package com.ecommerce.base;

import com.ecommerce.utils.ElementInteraction;
import com.ecommerce.utils.ElementListInteraction;
import com.ecommerce.utils.JavascriptExecutorUtils;
import com.ecommerce.utils.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

    protected final ElementInteraction elementInteraction;
    protected final ElementListInteraction elementListInteraction;
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.elementInteraction = new ElementInteraction(driver);
        this.elementListInteraction = new ElementListInteraction(driver);
        PageFactory.initElements(driver, this);
    }
}
