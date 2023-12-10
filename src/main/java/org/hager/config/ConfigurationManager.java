package org.hager.config;

import org.hager.utils.CustomAutomationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import static org.hager.config.ConfigConstants.*;
import static org.hager.utils.Constants.projectPath;

public class ConfigurationManager {
    private static final Properties properties;
    private static final String CONFIG_FILE_PATH = "//src//main//resources//StgEnvironment.properties";
    private static final Logger logger = LoggerFactory.getLogger(ConfigurationManager.class);

    static {
        properties = new Properties();
        loadPropertiesData();
    }

    public static void loadPropertiesData(){
        try {
            InputStream input = new FileInputStream( projectPath + CONFIG_FILE_PATH);
            properties.load(input);
            input.close();
        } catch (Exception e) {
            logger.error("An error occurred while loading configuration properties.", e);
            // Throw a custom exception for better error handling
            throw new CustomAutomationException("An error occurred while loading configuration properties.", e);
        }
    }

    public static String getBrowserName() {
        String browserFromSys = System.getProperty(BROWSER);
        return browserFromSys!= null ? browserFromSys : properties.getProperty(BROWSER);
    }
    public static String getBaseUrl() {
        String urlFromSys = System.getProperty(BASE_URL);
        return urlFromSys != null ? urlFromSys : properties.getProperty(BASE_URL);
    }
}

