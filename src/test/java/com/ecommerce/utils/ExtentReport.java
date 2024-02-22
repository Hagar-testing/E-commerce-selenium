package com.ecommerce.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.model.Media;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReport {

    private static ExtentReports report;
    private static ExtentTest test;

    public static void initReports() {
        System.out.println();
        report = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter("ExtentReports.html");
        report.attachReporter(spark);
        spark.config().setTheme(Theme.STANDARD);
        spark.config().setDocumentTitle("Extent Report");
        spark.config().setReportName("E-commerce Extent Report");
    }

    public static void createTest(String testCaseName) {
        test = report.createTest(testCaseName);
    }

    public static void removeTest(String testCaseName) {
        report.removeTest(testCaseName);
    }

    public static void info(String message) {
        if (test != null) {
            test.info(message);
        }
    }

    public static void info(Markup m) {
        test.info(m);
    }

    public static void pass(String message) {
        test.pass(message);
    }

    public static void pass(Markup m) {
        test.pass(m);
    }

    public static void fail(String message) {
        test.fail(message);
    }

    public static void fail(Markup m) {
        test.fail(m);
    }

    public static void fail(Throwable t) {
        test.fail(t);
    }

    public static void fail(Media media) {
        test.fail(media);
    }

    public static void skip(String message) {
        test.skip(message);
    }

    public static void skip(Markup m) {
        test.skip(m);
    }

    public static void skip(Throwable t) {
        test.skip(t);
    }

    public static void flushReports() {
        report.flush();
    }

}