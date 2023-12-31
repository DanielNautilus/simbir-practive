package org.globalsqa.tests;

import org.helpers.DriverHelper;
import org.helpers.LocalStorageHelper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

abstract class BaseTest {
    protected WebDriver driver;
    protected LocalStorageHelper localStorageHelper;

    @BeforeEach
    public void setup() {
        driver = DriverHelper.getDriver();
        localStorageHelper = new LocalStorageHelper(driver);
    }

    @AfterEach
    protected void teardown() {
        localStorageHelper.clearLocalStorage();
        DriverHelper.closeDriver();
    }

    @AfterAll
    protected static void teardownAll() {
        DriverHelper.quitDriver();
    }
}
