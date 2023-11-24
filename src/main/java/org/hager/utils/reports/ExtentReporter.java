package org.hager.utils.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import static org.hager.utils.Constants.extendReportsPath;

public class ExtentReporter {

    public static ExtentReports getReportObject(){
        String path = extendReportsPath + "index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("Order Flow");
        reporter.config().setDocumentTitle("Hager Ibrahim");
        ExtentReports reports = new ExtentReports();
        reports.attachReporter(reporter);
        reports.setSystemInfo("Tester", "Hager");
        return reports;
    }
}
