package org.globalsqa.helpers;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class NativeBrowserDialogHelper extends BaseHelper {
    private String alertText;

    public NativeBrowserDialogHelper(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void acceptAlert() {
        Alert alert = driver.switchTo().alert();
        alertText = alert.getText();
        alert.accept();
    }
    public String getAlertText(){
        return alertText;
    }
}
