package ru.smpl.tests;


import org.junit.jupiter.api.Test;
import ru.smpl.pages.SamplePage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

class SampleTest extends BaseUiTest {

    private final SamplePage samplePage = new SamplePage();

    @Test
    void openMainPage() {
        samplePage.openPage();
        $("").shouldBe(visible);
    }
}