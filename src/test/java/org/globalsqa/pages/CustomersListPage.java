package org.globalsqa.pages;

import io.qameta.allure.Step;
import org.globalsqa.components.CustomersTableComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CustomersListPage extends BasePage {
    @FindBy(css = "input[ng-model='searchCustomer']")
    private WebElement searchCustomerInput;

    public CustomersTableComponent customersTableComponent;

    public CustomersListPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        customersTableComponent = new CustomersTableComponent(driver);
    }

    @Step("Search customer by therm: {searchTerm}")
    public CustomersListPage searchCustomer(String searchTerm) {
        setInputValue(searchCustomerInput,searchTerm);
        return this;
    }

}
