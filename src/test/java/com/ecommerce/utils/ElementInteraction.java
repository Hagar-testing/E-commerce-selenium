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
    private final WaitUtils waitUtils;
    private final JavascriptExecutorUtils javascriptExecutorUtils;

    public ElementInteraction(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
        this.javascriptExecutorUtils = new JavascriptExecutorUtils(driver);

    }

    // Generic method to handle element visibility based on locator type
    public WebElement locateElement(WebElement element, LocatorType type) {
        switch (type) {
            case WITHOUT_WAIT -> {
                return element;
            }
            case WITH_WAIT, FULL_WAIT -> {
                return waitUtils.waitForVisibilityOfElement(element);
            }
            default -> throw new IllegalArgumentException("Invalid locator type");
        }
    }


    // Method to perform a simple click with element visibility wait
    public ElementInteraction simpleClick(WebElement element, LocatorType type) {
        locateElement(element,type).click();
        logElementActionStep(element, "Simple click on");
        return this;
    }

    // Overloaded method with default value for LocatorType
    public ElementInteraction simpleClick(WebElement element) {
        return simpleClick(element,WITHOUT_WAIT);
    }

    // Method to perform a click with hover and element visibility wait
    public ElementInteraction clickWithHover(WebElement element, LocatorType type) {
        Actions actions = new Actions(driver);
        actions.moveToElement(locateElement(element, type)).perform();
        element.click();
        return this;
    }

    // Method to perform a click using JavaScript and element visibility wait
    public ElementInteraction javascriptClick(WebElement element, LocatorType type) {
        WebElement element1 = locateElement(element, type);
        javascriptExecutorUtils.executeJavaScriptClick(element1);
        logElementActionStep(element, "javascript click on");

        return this;
    }

    public ElementInteraction javascriptClick(WebElement element){
        return javascriptClick(element, WITHOUT_WAIT);
    }

    // Other methods like type, select, etc. can be added here

    // Method to handle the full locating strategy (similar to your original locatingElementStrategy)


    public ElementInteraction setInput(WebElement element, String text, LocatorType type){
        locateElement(element,type).sendKeys(text);
        logElementActionStep(element, "set input [ " + text + " ] to ");

        return this;
    }

    public ElementInteraction setInput(WebElement element, String text){
        return setInput(element,text, WITHOUT_WAIT);
    }


    // Method to set input text using JavaScriptExecutor
    public ElementInteraction setInputWithJavaScriptExecutor(WebElement element, String text, LocatorType type) {
        javascriptExecutorUtils.sendInput(locateElement(element,type), text);
        logElementActionStep(element, "set input [ " + text + "] with java script executor to ");

        return this;
    }

    public ElementInteraction setInputWithJavaScriptExecutor(WebElement element, String text) {
        return setInputWithJavaScriptExecutor(element,text,WITHOUT_WAIT);
    }

    // Method to simulate Backspace key press using sendKeys
    public ElementInteraction simulateBackspace(WebElement element, LocatorType type) {
        locateElement(element,type).sendKeys(Keys.BACK_SPACE);
        logElementActionStep(element, "Click on back space");

        return this;
    }

    public ElementInteraction simulateBackspace(WebElement element) {
        return simulateBackspace(element, WITHOUT_WAIT);
    }

    public ElementListInteraction withList() {
        return new ElementListInteraction(driver);
    }

    private static void logElementActionStep(WebElement element,String action) {

        try {
            String elementName = element.getAccessibleName();
            String actionText = (elementName != null && !elementName.isEmpty()) ? action : element.toString();
            Logger.logStep("[Element Action] " + action + " [" + actionText + "] element");

        } catch (Exception e){
            Logger.logStep("[Element Action] " + action + " [" + element + "] element");

        }

    }
}
