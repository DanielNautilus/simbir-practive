package org.globalsqa.pages;

import org.globalsqa.helpers.ConfigReader;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected String path;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    protected String getUri() {
        String url = ConfigReader.getProperty("base.url");
        return url + path;
    }

    public BasePage navigate() {
        driver.get(getUri());
        return this;
    }

    protected void setInputValue(WebElement element, String value) {
        element.clear();
        element.sendKeys(value);
    }

}
