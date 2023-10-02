package org.globalsqa.tests;

import io.qameta.allure.*;
import org.globalsqa.helpers.LocalStorageHelper;
import org.globalsqa.helpers.NativeBrowserDialogHelper;
import org.globalsqa.models.CustomerModel;
import org.globalsqa.pages.AddCustomerPage;
import org.globalsqa.pages.ManagerPage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

@Epic("Customer Management")
@Feature("Creation")
@DisplayName("Create customer creation tests")
@Execution(ExecutionMode.CONCURRENT)
public class CustomerCreationTest extends BaseTests {

    @Test
    @Epic("Customer Management")
    @Feature("Creation")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Create customer: added, set in local storage")
    @Description("Set all fields creat customer, added to local storage")
    @Execution(ExecutionMode.CONCURRENT)
    public void Test_CustomerCreation_singleAdded_recordInLocalStorage() {
        // Arrange
        int expectedMaxUserId = 6;
        CustomerModel expectedCustomer = new CustomerModel("fName","lName","12345",expectedMaxUserId);
        String expectedDialogText = "Customer added successfully with customer id :" + expectedMaxUserId;
        LocalStorageHelper localStorageHelper = new LocalStorageHelper(driver);

        // Act
        ManagerPage managerPage = new ManagerPage(driver).navigate();
        AddCustomerPage addCustomerPage = managerPage.clickAddCustomer();
        addCustomerPage
                .setFirstName(expectedCustomer.getFirstName())
                .setLastName(expectedCustomer.getLastName())
                .setPostCode(expectedCustomer.getPostCode())
                .submitCreationForm();

        // Assert
        assertLocalStorageContainsCustomer(expectedCustomer, localStorageHelper);
        assertMaxUserIdEquivalent(expectedMaxUserId, localStorageHelper);
        assertDialogText(expectedDialogText, addCustomerPage);
    }
    @Step("Local storage: contains expected Customer")
    private void assertLocalStorageContainsCustomer(CustomerModel expectedCustomer, LocalStorageHelper localStorageHelper) {
        var customers = localStorageHelper.getCustomersFromLocalStorage();
        boolean customerFound = customers.stream()
                .anyMatch(customer ->
                        customer.getFirstName().equals(expectedCustomer.getFirstName())
                                && customer.getLastName().equals(expectedCustomer.getLastName())
                                && customer.getPostCode().equals(expectedCustomer.getPostCode())
                                && customer.getId().equals(expectedCustomer.getId())
                );
        Assertions.assertTrue(customerFound, "New customer not found in the local storage");
    }
    @Step("Local storage: expected {expectedMaxUserId} equivalent actual exist")
    private void assertMaxUserIdEquivalent(int expectedMaxUserId, LocalStorageHelper localStorageHelper) {
        int actualMaxUserId = localStorageHelper.getMaxUserId();
        Assertions.assertEquals(expectedMaxUserId, actualMaxUserId, "Last added customer id not increased");
    }
    @Step("Dialog: text of dialog equivalent")
    private void assertDialogText(String expectedDialogText, AddCustomerPage addCustomerPage) {
        String actualDialogText = addCustomerPage.getConfirmCreationAlertText();
        Assertions.assertEquals(expectedDialogText, actualDialogText, "Dialog text does not match expected");
    }



}
