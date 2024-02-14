package com.ecommerce.utils;

import com.ecommerce.enums.LocatorType;
import com.ecommerce.enums.LocatorType.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.ecommerce.enums.LocatorType.WITHOUT_WAIT;


public class ElementInteraction {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final JavascriptExecutorUtils javascriptExecutorUtils;



    public ElementInteraction(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.javascriptExecutorUtils = new JavascriptExecutorUtils(driver);

    }

    // Generic method to handle element visibility based on locator type
    public WebElement locateElement(By locator, LocatorType type) {
        switch (type) {
            case WITHOUT_WAIT -> {
                return driver.findElement(locator);
            }
            case WITH_WAIT -> {
                return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            }
            case FULL_WAIT -> {
                locatingElementStrategy(locator);
                return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            }
            default -> throw new IllegalArgumentException("Invalid locator type");
        }
    }

    public WebElement locateElement(By locator){
        return locateElement(locator, WITHOUT_WAIT);
    }

    // Method to perform a simple click with element visibility wait
    public ElementInteraction simpleClick(By locator, LocatorType type) {
        WebElement element = locateElement(locator, type);
        element.click();
        return this;
    }

    // Overloaded method with default value for LocatorType
    public ElementInteraction simpleClick(By locator) {
        return simpleClick(locator, WITHOUT_WAIT);
    }

    // Method to perform a click with hover and element visibility wait
    public ElementInteraction clickWithHover(By locator, LocatorType type) {
        WebElement element = locateElement(locator, type);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
        element.click();
        return this;
    }

    // Method to perform a click using JavaScript and element visibility wait
    public ElementInteraction javascriptClick(By locator, LocatorType type) {
        WebElement element = locateElement(locator, type);
        javascriptExecutorUtils.executeJavaScriptClick(element);
        return this;
    }

    public ElementInteraction javascriptClick(By locator){
        return javascriptClick(locator, WITHOUT_WAIT);
    }

    // Other methods like type, select, etc. can be added here

    // Method to handle the full locating strategy (similar to your original locatingElementStrategy)
    private void locatingElementStrategy(By locator) {
        // Implement the full locating strategy here
    }

    public ElementInteraction setInput(By locator, String text, LocatorType type){
        locateElement(locator,type).sendKeys(text);
        return this;
    }

    public ElementInteraction setInput(By locator, String text){
        return setInput(locator,text, WITHOUT_WAIT);
    }


    // Method to set input text using JavaScriptExecutor
    public ElementInteraction setInputWithJavaScriptExecutor(By locator, String text, LocatorType type) {
        javascriptExecutorUtils.sendInput(locateElement(locator,type), text);
        return this;
    }

    public ElementInteraction setInputWithJavaScriptExecutor(By locator, String text) {
        return setInputWithJavaScriptExecutor(locator,text,WITHOUT_WAIT);
    }

    // Method to simulate Backspace key press using sendKeys
    public ElementInteraction simulateBackspace(By locator, LocatorType type) {
        locateElement(locator,type).sendKeys(Keys.BACK_SPACE);
        return this;
    }

    public ElementInteraction simulateBackspace(By locator) {
        return simulateBackspace(locator, WITHOUT_WAIT);
    }

    public ElementListInteraction withList() {
        return new ElementListInteraction(driver);
    }
}
