package com.ecommerce.listeners;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.ecommerce.utils.ExtentReport;
import com.ecommerce.utils.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.*;

public class TestngListener implements ISuiteListener, ITestListener, IInvokedMethodListener {

	@Override
	public void onStart(ISuite suite) {
		System.out.println("\n" + "**********************************************");
		System.out.println("Starting Test Suite; By Hager ! *");
		System.out.println("**********************************************" + "\n");
		ExtentReport.initReports();
	}

	@Override
	public void onFinish(ISuite suite) {
		System.out.println("\n" + "**********************************************");
		System.out.println("Finished Test Suite; By Hager ! *");
		System.out.println("**********************************************" + "\n");
		ExtentReport.flushReports();
	}

	@Override
	public void onStart(ITestContext context) {
		System.out.println("\n" + "**************************************************** " + "Test: ["
				+ context.getName() + "] Started" + " ****************************************************" + "\n");
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("\n" + "**************************************************** " + "Test: ["
				+ context.getName() + "] Finished" + " ****************************************************" + "\n");
	}



	@Override
	public void onTestSuccess(ITestResult result) {
		ExtentReport.pass(MarkupHelper.createLabel(result.getMethod().getMethodName() + " Passed!", ExtentColor.GREEN));
	}

	@Override
	public void onTestFailure(ITestResult result) {
		ITestContext context = result.getTestContext();
		WebDriver driver = (WebDriver) context.getAttribute("driver");
		if (driver != null) {
//	    Logger.attachScreenshotToAllureReport(driver);
			ExtentReport.fail(Logger.attachScreenshotToExtentReport(driver));
		}
		ExtentReport.fail(MarkupHelper.createLabel(result.getMethod().getMethodName() + " Failed!", ExtentColor.RED));
		if (result.getThrowable() != null) {
			ExtentReport.fail(result.getThrowable());
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		ExtentReport.skip(MarkupHelper.createLabel(result.getMethod().getMethodName() + " Skipped!", ExtentColor.YELLOW));
		if (result.getThrowable() != null) {
			ExtentReport.skip(result.getThrowable());
		}
	}


	//region IInvokedMethodListener
	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		ITestNGMethod testMethod = method.getTestMethod();


		if (!method.isConfigurationMethod()) {
			System.out.println("\n" + "============================================================================================");

			if (testMethod.getDescription() != null && !testMethod.getDescription().equals("")) {
				ExtentReport.createTest(testMethod.getDescription());
			} else {
				ExtentReport.createTest(testResult.getName());
			}
			System.out.println("Starting Test Case: [" + testResult.getName() + "]");
			System.out.println("============================================================================================" + "\n");

		}

	}

	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
		ITestContext context = testResult.getTestContext();
		WebDriver driver = (WebDriver) context.getAttribute("driver");
		if ( testResult.getStatus() == ITestResult.FAILURE && driver != null) {
			Logger.attachScreenshotToAllureReport(driver);
		}

		System.out.println("\n" + "===========================================================================================");
		if (method.isConfigurationMethod()) {
			System.out.println("Finished Configuration Method (Setup or Teardown): [" + testResult.getName() + "]");
		} else {
			System.out.println("Finished Test Case: [" + testResult.getName() + "]");
		}
		System.out.println("===========================================================================================" + "\n");
	}

	//endregion
}
