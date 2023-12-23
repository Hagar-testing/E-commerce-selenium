package com.ecommerce.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;
import static com.ecommerce.constants.ConfigConstants.*;

public class DriverFactory {

    public WebDriver initializeDriver() {
        String browser = System.getProperty(BROWSER, CHROME);
        WebDriver driver;
        switch (browser) {
            case CHROME -> {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
            }
            case FIREFOX -> {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            }
            case EDGE -> {
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
            }
            default -> throw new RuntimeException("The browser is not supported!");
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        return driver;
    }
}
