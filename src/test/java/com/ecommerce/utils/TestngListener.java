package com.ecommerce.utils;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;

public class TestngListener implements ITestListener, ISuiteListener {


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
	public void onStart(ISuite suite) {
		ExtentReport.initReports();
	}

	@Override
	public void onFinish(ISuite suite) {
		ExtentReport.flushReports();
	}
}