package ru.smpl.config;

import com.codeborne.selenide.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class TestConfig {

    private static final String CONFIG_FILE = "test.properties";
    private static final Properties PROPERTIES = loadProperties();

    private TestConfig() {
    }

    public static void apply() {
        Configuration.baseUrl = get("base.url");
        Configuration.browser = get("browser");
        Configuration.browserSize = get("browser.size");
        Configuration.timeout = Long.parseLong(get("timeout"));
        Configuration.pageLoadTimeout = Long.parseLong(get("page.load.timeout"));
        Configuration.headless = Boolean.parseBoolean(get("headless"));
    }

    public static String get(String key) {
        String systemValue = System.getProperty(key);
        if (systemValue != null && !systemValue.isBlank()) {
            return systemValue;
        }
        return PROPERTIES.getProperty(key);
    }

    private static Properties loadProperties() {
        Properties properties = new Properties();
        try (InputStream inputStream = TestConfig.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (inputStream == null) {
                throw new IllegalStateException("Configuration file not found: " + CONFIG_FILE);
            }
            properties.load(inputStream);
            return properties;
        } catch (IOException e) {
            throw new IllegalStateException("Failed to read configuration file: " + CONFIG_FILE, e);
        }
    }
}
