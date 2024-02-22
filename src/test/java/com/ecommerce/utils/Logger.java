package com.ecommerce.utils;

import io.qameta.allure.Step;

public class Logger {

    @Step("{message}")
    public static void logStep(String message) {
        System.out.println("<" +   "> " + message);
        ExtentReport.info(message);
    }
}
