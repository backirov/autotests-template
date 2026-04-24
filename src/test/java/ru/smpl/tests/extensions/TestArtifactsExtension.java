package ru.smpl.tests.extensions;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class TestArtifactsExtension implements TestExecutionExceptionHandler {

    @Override
    public void handleTestExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {
        attachArtifacts();
        throw throwable;
    }

    private void attachArtifacts() {
        if (!WebDriverRunner.hasWebDriverStarted()) {
            return;
        }

        attachScreenshot();
        attachPageSource();
    }

    private void attachScreenshot() {
        try {
            byte[] screenshot = ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
            Allure.getLifecycle().addAttachment("Screenshot on failure", "image/png", "png", screenshot);
        } catch (Exception ignored) {
            // Do not mask the original test failure if attachment creation fails.
        }
    }

    private void attachPageSource() {
        try {
            byte[] pageSource = Objects.requireNonNull(WebDriverRunner.source()).getBytes(StandardCharsets.UTF_8);
            Allure.getLifecycle().addAttachment("Page source on failure", "text/html", "html", pageSource);
        } catch (Exception ignored) {
            // Do not mask the original test failure if attachment creation fails.
        }
    }
}