package com.ecommerce.utils;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.model.Media;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Logger {

    private static org.apache.logging.log4j.Logger loog = LogManager.getLogger(Logger.class);
    @Step("{message}")
    public static void logStep(String message) {
        System.out.println("<" +   "> " + message);
        loog.info("<" +   "> " + message);
        ExtentReport.info(message);
    }

    @Attachment(value = "Full Page Screenshot", type = "image/png")
    public static byte[] attachScreenshotToAllureReport(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
//	Allure.addAttachment("Full Page Screenshot", "image/png",
//		new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)), ".png");
    }

    public static Media attachScreenshotToExtentReport(WebDriver driver) {
        return MediaEntityBuilder.createScreenCaptureFromBase64String(
                ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64), "Full Page Screenshot").build();
    }
}
