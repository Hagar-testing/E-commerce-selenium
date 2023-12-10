package utils.base;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.hager.config.ConfigurationManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.time.Duration;

import static org.hager.config.ConfigConstants.*;

public class BaseTest {
    public WebDriver driver;

    public void initializeDriver() {
        String browserName = ConfigurationManager.getBrowserName();

        if (browserName.contains(CHROME)) {
            ChromeOptions options =  new ChromeOptions();
            if(browserName.contains(HEADLESS)){
                options.addArguments(HEADLESS);
            }
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(options);
            driver.manage().window().setSize(new Dimension(1440,900));
        } else if (browserName.equalsIgnoreCase(FIREFOX)) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();

        } else if (browserName.equalsIgnoreCase(EDGE)) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();


    }

    @BeforeClass
    public void setup(){
        initializeDriver();
    }


    @AfterClass
    public void tearDown(){
        driver.close();
    }
}
