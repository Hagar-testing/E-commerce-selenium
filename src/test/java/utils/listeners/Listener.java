package utils.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

import static org.hager.utils.Constants.extendReportsPath;
import static org.hager.utils.reports.ExtentReporter.getReportObject;

public class Listener implements ITestListener {

    ExtentReports reports = getReportObject();
    ExtentTest extentTest;

    ThreadLocal<ExtentTest> threadLocal = new ThreadLocal<>();
    WebDriver driver;

    @Override
    public void onTestStart(ITestResult result) {
        extentTest = reports.createTest(result.getMethod().getMethodName());
        threadLocal.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        threadLocal.get().fail(result.getThrowable());
        String screenshotPath;
        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
            screenshotPath = takeScreenshot(result.getMethod().getMethodName());
            threadLocal.get().addScreenCaptureFromPath(screenshotPath,result.getMethod().getMethodName());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test skipped: " + result.getName());
    }


    @Override
    public void onFinish(ITestContext context) {
        reports.flush();
    }

    public String takeScreenshot(String testCaseName) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String screenshotPath = extendReportsPath + testCaseName + ".png";
        File file = new File( screenshotPath);
        FileUtils.copyFile(source, file);
        return screenshotPath;
    }
}
