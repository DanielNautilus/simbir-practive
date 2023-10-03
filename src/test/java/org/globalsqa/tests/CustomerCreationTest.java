package org.globalsqa.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import org.globalsqa.helpers.LocalStorageHelper;
import org.globalsqa.models.CustomerModel;
import org.globalsqa.pages.AddCustomerPage;
import org.globalsqa.pages.ManagerPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.util.List;

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
    @Description("Set all fields create customer, added to local storage")
    @Execution(ExecutionMode.CONCURRENT)
    public void testCustomerCreationSingleAddedRecordInLocalStorage() {
        // Arrange
        ManagerPage managerPage = new ManagerPage(driver).navigate();
        LocalStorageHelper localStorageHelper = new LocalStorageHelper(driver);
        int expectedUserId = localStorageHelper.getMaxUserId() + 1;
        CustomerModel expectedCustomer = new CustomerModel(
                "fName",
                "lName",
                "12345",
                expectedUserId
        );
        String expectedDialogText = "Customer added successfully with customer id :" + expectedUserId;

        // Act
        AddCustomerPage addCustomerPage = managerPage.navigateAddCustomerPage();
        addCustomerPage
                .setFirstName(expectedCustomer.getFirstName())
                .setLastName(expectedCustomer.getLastName())
                .setPostCode(expectedCustomer.getPostCode())
                .submitCreationForm();

        // Assert
        assertLocalStorageContainsCustomer(expectedCustomer, localStorageHelper.getCustomersFromLocalStorage());
        assertMaxUserIdEquivalent(expectedUserId, localStorageHelper.getMaxUserId());
        assertDialogText(expectedDialogText, addCustomerPage.getConfirmCreationAlertText());
    }

    @Step("Local storage: contains expected Customer")
    private void assertLocalStorageContainsCustomer(CustomerModel expectedCustomer, List<CustomerModel> customers) {
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
    private void assertMaxUserIdEquivalent(int expectedMaxUserId, int actualMaxUserId) {
        Assertions.assertEquals(expectedMaxUserId, actualMaxUserId, "Last added customer id not increased");
    }

    @Step("Dialog: text of dialog equivalent")
    private void assertDialogText(String expectedDialogText, String actualDialogText) {
        Assertions.assertEquals(expectedDialogText, actualDialogText, "Dialog text does not match expected");
    }


}
