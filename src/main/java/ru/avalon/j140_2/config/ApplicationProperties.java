package ru.avalon.j140_2.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Extend standard java class Properties<br>
 * Implements the Singleton pattern via "Class Holder Singleton"
 */
public final class ApplicationProperties extends Properties {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationProperties.class);

    private ApplicationProperties() {
    }

    public static ApplicationProperties getInstance() {
        final ApplicationProperties appProperties = ApplicationPropertiesHolder.HOLDER_INSTANCE;
        File file = new File("src/main/resources/Application.properties");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            appProperties.load(new FileInputStream(file));
        } catch (IOException ioe) {
            logger.error("Error file reading", ioe);
        }
        return appProperties;
    }

    private static class ApplicationPropertiesHolder {
        private static final ApplicationProperties HOLDER_INSTANCE = new ApplicationProperties();
    }
}
