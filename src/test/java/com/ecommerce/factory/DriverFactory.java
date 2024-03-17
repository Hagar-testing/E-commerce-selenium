package com.ecommerce.factory;

import com.ecommerce.enums.BrowserType;
import org.openqa.selenium.WebDriver;

import java.time.Duration;
import static com.ecommerce.constants.ConfigConstants.*;
import static com.ecommerce.enums.BrowserType.CHROME;


public class DriverFactory {

    public WebDriver initializeDriver() {
        String browserKey = System.getProperty(BROWSER, CHROME.getKey());
        WebDriver driver;

        BrowserType browser = BrowserType.getByKey(browserKey.toLowerCase());
        switch (browser) {
            case CHROME -> {
                ChromeBaseDriverFactoryImpl ChromTestFactoryImpl = new ChromeBaseDriverFactoryImpl();
                driver = ChromTestFactoryImpl.getDriver();
            }
            case FIREFOX -> {
                FireFoxBaseDriverFactoryImpl fireFoxWebDriverFactory = new FireFoxBaseDriverFactoryImpl();
                driver = fireFoxWebDriverFactory.getDriver();
            }
            case EDGE -> {
                EdgeBaseDriverFactoryImpl edgeWebDriverFactory = new EdgeBaseDriverFactoryImpl();
                driver = edgeWebDriverFactory.getDriver();
            }
            default -> throw new IllegalArgumentException("Unsupported browser: " + browserKey);
        }

        configureDriver(driver);
        return driver;
    }

    private void configureDriver(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }
}
