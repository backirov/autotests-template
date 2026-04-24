package ru.smpl.tests;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.smpl.config.TestConfig;
import ru.smpl.tests.extensions.TestArtifactsExtension;

@ExtendWith(TestArtifactsExtension.class)
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
