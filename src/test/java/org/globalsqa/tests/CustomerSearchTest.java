package org.globalsqa.tests;

import io.qameta.allure.*;
import org.helpers.LocalStorageHelper;
import org.globalsqa.models.CustomerModel;
import org.globalsqa.pages.AddCustomerPage;
import org.globalsqa.pages.CustomersListPage;
import org.globalsqa.pages.ManagerPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.WebElement;
@Epic("Customer Management")
@Feature("Search")
@DisplayName("Create customer searching tests")
@Execution(ExecutionMode.CONCURRENT)
public class CustomerSearchTest extends BaseTest {

    @Test
    @Epic("Customer Management")
    @Feature("Search")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Search customer: displayed at customers list")
    @Description("Searching customer exist ")
    @Execution(ExecutionMode.CONCURRENT)
    public void testCustomerSearchFullNameMatchCustomerExist() {
        //Arrange
        ManagerPage managerPage = new ManagerPage(driver).navigate();
        LocalStorageHelper localStorageHelper = new LocalStorageHelper(driver);
        int currentMaxId = localStorageHelper.getMaxUserId();
        CustomerModel expectedCustomer = new CustomerModel("Daniel","Yurtaev","444431",currentMaxId + 1);
        //Pre-condition: Added new customer for search
        AddCustomerPage addCustomerPage = managerPage.navigateAddCustomerPage();
        addCustomerPage
                .createCustomer(
                        expectedCustomer.getFirstName(),
                        expectedCustomer.getLastName(),
                        expectedCustomer.getPostCode()
                );

        // Act
        CustomersListPage customerListPage = addCustomerPage.navigateCustomersListPage();
        WebElement customerRow = customerListPage
                .searchCustomer(expectedCustomer.getFirstName())
                .customersTableComponent
                .findCustomerByName(expectedCustomer.getFirstName());

        //Assert
        Assertions.assertNotNull(customerRow, "Customer with name: " + expectedCustomer.getFirstName() + " not found"); //is Customer with name exist in customers list
    }
}
