package org.globalsqa.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

abstract class BaseComponent {
    protected WebDriver driver;

    protected BaseComponent(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

}
