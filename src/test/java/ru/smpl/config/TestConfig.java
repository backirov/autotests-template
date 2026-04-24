package ru.smpl.config;

import com.codeborne.selenide.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class TestConfig {

    private static final String PROFILE_PROPERTY = "test.profile";
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
        String configFile = resolveConfigFileName();

        System.out.println("Config file: " + configFile);

        try (InputStream inputStream = TestConfig.class.getClassLoader().getResourceAsStream(configFile)) {
            if (inputStream == null) {
                throw new IllegalStateException("Configuration file not found: " + configFile);
            }
            properties.load(inputStream);
            return properties;
        } catch (IOException e) {
            throw new IllegalStateException("Failed to read configuration file: " + configFile, e);
        }
    }

    private static String resolveConfigFileName() {
        String profile = resolveProfile();
        return profile + ".properties";
    }

    private static String resolveProfile() {
        String profile = System.getProperty(PROFILE_PROPERTY);
        if (profile != null && !profile.isBlank()) {
            return profile;
        }

        return null;
    }
}