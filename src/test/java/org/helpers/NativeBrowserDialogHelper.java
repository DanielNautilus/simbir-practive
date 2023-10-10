package org.helpers;

import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class NativeBrowserDialogHelper extends BaseHelper {
    private String alertText;

    public NativeBrowserDialogHelper(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Step("Alert dialog confirm")
    public void acceptAlert() {
        Alert alert = driver.switchTo().alert();
        alertText = alert.getText();
        alert.accept();
    }

    @Step("Get Alert text at alert dialog")
    public String getAlertText(){
        return alertText;
    }
}
