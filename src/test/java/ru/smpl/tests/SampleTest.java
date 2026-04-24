package ru.smpl.tests;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.smpl.pages.SamplePage;

@Disabled("Template test class for future UI tests")
class SampleTest extends BaseUiTest {

    private final SamplePage samplePage = new SamplePage();

    @Test
    void openMainPage() {
        samplePage.openPage();
    }
}