package org.globalsqa.pages;

import io.qameta.allure.Step;
import org.globalsqa.helpers.ConfigReader;
import org.globalsqa.helpers.NativeBrowserDialogHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddCustomerPage extends BasePage {
    private final NativeBrowserDialogHelper dialogHelper;

    @FindBy(css = "input[ng-model='fName']")
    private WebElement firstNameInput;

    @FindBy(css = "input[ng-model='lName']")
    private WebElement lastNameInput;

    @FindBy(css = "input[ng-model='postCd']")
    private WebElement postCodeInput;

    @FindBy(css = "form[name='myForm'] button[type='submit']")
    private WebElement formSubmit;

    public AddCustomerPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
        path = ConfigReader.getProperty("managerPage.addCustomer.url");
        dialogHelper = new NativeBrowserDialogHelper(driver);
    }
    @Step("set First Name: {firstName}")
    public AddCustomerPage setFirstName(String firstName){
        setInputValue(firstNameInput,firstName);
        return this;
    }
    @Step("set Last Name: {lastName}")
    public AddCustomerPage setLastName(String lastName){
        setInputValue(lastNameInput,lastName);
        return this;
    }
    @Step("set Post Code: {postCode}")
    public AddCustomerPage setPostCode(String postCode){
        setInputValue(postCodeInput,postCode);
        return this;
    }
    @Step("Submit Customer creation, confirm dialog opens")
    public AddCustomerPage submitCreationForm(){
        formSubmit.click(); //Confirm native dialog opens
        dialogHelper.acceptAlert(); //Confirm native dialog alert
        return this;
    }
    @Step("Get dialog alert text")
    public String getConfirmCreationAlertText(){
        return dialogHelper.getAlertText(); //Confirm native dialog alert
    }
}
