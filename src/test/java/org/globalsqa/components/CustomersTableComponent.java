package org.globalsqa.components;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CustomersTableComponent extends BaseComponent{
    @FindBy(css = "tbody tr.ng-scope")
    private List<WebElement> customerRows;

    @FindBy(xpath = "//a[contains(@ng-click, \"sortType = 'fName'\")]")
    private WebElement firstNameColumn;


    public CustomersTableComponent(WebDriver driver) {
        super(driver);
    }
    @Step("Search customer in customers lists by name {name}")
    public WebElement findCustomerByName(String name) {
        return customerRows.stream()
                .filter(customer -> customer.getText().contains(name))
                .findFirst()
                .orElse(null);
    }
    public List<WebElement> getAllDisplayedCustomers(){
        return customerRows;
    }
    @Step("Sort by first name column")
    public CustomersTableComponent sortByFirstName(){
        firstNameColumn.click();
        return this;
    }
}
