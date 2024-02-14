package com.ecommerce.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ElementListInteraction {
    private final WebDriver driver;

    public ElementListInteraction(WebDriver driver){
        this.driver = driver;
    }
    public ElementInteraction withElement() {
        return new ElementInteraction(driver);
    }

    public List<WebElement> locateElementList(By locator){
        return driver.findElements(locator);
    }
}