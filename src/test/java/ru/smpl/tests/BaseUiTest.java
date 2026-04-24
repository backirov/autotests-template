package ru.smpl.tests;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import ru.smpl.config.TestConfig;

public abstract class BaseUiTest {

    @BeforeAll
    static void setUpSuite() {
        TestConfig.apply();
    }

    @AfterEach
    void tearDown() {
        Selenide.closeWebDriver();
    }
}
