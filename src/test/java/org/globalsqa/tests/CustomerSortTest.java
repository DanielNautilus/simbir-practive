package org.globalsqa.tests;

import io.qameta.allure.*;
import org.globalsqa.helpers.SortHelper;
import org.globalsqa.pages.CustomersListPage;
import org.globalsqa.pages.ManagerPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;
@Epic("Customer Management")
@Feature("Sort")
@DisplayName("Create customer sorting tests")
@Execution(ExecutionMode.CONCURRENT)
public class CustomerSortTest extends BaseTests {
    @Test
    @Epic("Customer Management")
    @Feature("Sort")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Sort customer: customers descending order")
    @Description("Set all 5 customers, in descending order")
    @Execution(ExecutionMode.CONCURRENT)
    public void Test_CustomerSort_SortByFirstNameDesc_customersSortedByFirstNameInDescOrder() {
        // Arrange
        String sortOrder = "descending";
        ManagerPage managerPage = new ManagerPage(driver).navigate();
        CustomersListPage customerListPage = managerPage.clickCustomers();

        // Act
        List<String> customerNames =customerListPage
                .customersTableComponent
                .sortByFirstName() //sort desc
                .getAllDisplayedCustomers()
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

        // Assert
        boolean isSortedAsc = SortHelper.isSortedDesc(customerNames);
        Assertions.assertTrue(isSortedAsc, "Customers are not sorted in " + sortOrder + " order by name.");
    }
}
