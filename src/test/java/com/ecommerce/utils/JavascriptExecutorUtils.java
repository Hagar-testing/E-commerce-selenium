package com.ecommerce.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavascriptExecutorUtils {

    private final JavascriptExecutor jsExecutor;


    public JavascriptExecutorUtils(WebDriver driver) {
        this.jsExecutor = (JavascriptExecutor) driver;
    }

    public void executeJavaScriptClick(WebElement element) {
        jsExecutor.executeScript("arguments[0].click();", element);
    }

    public void sendInput(WebElement element, String text){
        jsExecutor.executeScript("arguments[0].value = arguments[1];", element, text);

    }

}
