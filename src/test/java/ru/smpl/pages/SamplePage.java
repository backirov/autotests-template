package ru.smpl.pages;

import static com.codeborne.selenide.Selenide.open;

public class SamplePage {

    public SamplePage openPage() {
        open("/");
        return this;
    }
}
