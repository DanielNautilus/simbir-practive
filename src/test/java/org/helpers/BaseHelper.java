package org.helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

abstract class BaseHelper {
    protected WebDriver driver;
    protected BaseHelper(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
}
