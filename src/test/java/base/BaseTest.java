package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.hager.config.ConfigurationManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;

import java.time.Duration;

import static org.hager.config.ConfigConstants.*;

public class BaseTest {
    public WebDriver driver;

    public void initializeDriver() {
        String browserName = ConfigurationManager.getBrowserName();

        if (browserName.equalsIgnoreCase(CHROME)) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
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
