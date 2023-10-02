package org.globalsqa.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CustomersTableComponent extends BaseComponent{
    @FindBy(xpath = "//tbody//tr")
    private List<WebElement> customerRows;

    @FindBy(xpath = "/html/body/div/div/div[2]/div/div[2]/div/div/table/thead/tr/td[1]/a")
    private WebElement firstNameColumn;


    public CustomersTableComponent(WebDriver driver) {
        super(driver);
    }

    public WebElement findCustomerByName(String name) {
        return customerRows.stream()
                .filter(customer -> customer.getText().contains(name))
                .findFirst()
                .orElse(null);
    }
    public List<WebElement> getAllDisplayedCustomers(){
        return customerRows;
    }
    public CustomersTableComponent sortByFirstName(){
        firstNameColumn.click();
        return this;
    }
}
