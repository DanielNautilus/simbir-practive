package org.globalsqa.pages;

import org.globalsqa.helpers.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ManagerPage extends  BasePage{

    @FindBy(css = "button[ng-class='btnClass1']")
    private WebElement navigateCustomerCreationButton;

    @FindBy(xpath = "//div[@class='center']//button[contains(text(), 'Customers')]")
    private WebElement navigateCustomersListButton;

    public ManagerPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        path = ConfigReader.getProperty("managerPage.url");
    }

    @Override
    public ManagerPage navigate() {
        driver.get(getUri());
        return this;
    }

    public AddCustomerPage clickAddCustomer(){
        navigateCustomerCreationButton.click();
        return new AddCustomerPage(driver);
    }
    public CustomersListPage clickCustomers(){
        navigateCustomersListButton.click();
        return new CustomersListPage(driver);
    }
}
