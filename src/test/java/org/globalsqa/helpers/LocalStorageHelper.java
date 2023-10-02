package org.globalsqa.helpers;
import org.json.JSONArray;
import org.json.JSONObject;
import org.globalsqa.models.CustomerModel;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class LocalStorageHelper extends BaseHelper{
    public LocalStorageHelper(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }
    public int getMaxUserId() {
        String localStorageData = (String) ((JavascriptExecutor) driver).executeScript(
                "return window.localStorage.getItem('maxUserId');");

        int maxUserId = 0;

        if (localStorageData != null) {
            try {
                maxUserId = Integer.parseInt(localStorageData);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        return maxUserId;
    }
    public List<CustomerModel> getCustomersFromLocalStorage() {
        String localStorageData = (String) ((JavascriptExecutor) driver).executeScript(
                "return window.localStorage.getItem('User');");
        List<CustomerModel> customers = new ArrayList<>();

        if (localStorageData != null) {
            JSONObject localStorageObject = new JSONObject(localStorageData);

            for (String customerId : localStorageObject.keySet()) {
                JSONObject customerObject = localStorageObject.getJSONObject(customerId);

                CustomerModel customer = new CustomerModel(
                        customerObject.getString("fName"),
                        customerObject.getString("lName"),
                        customerObject.getString("postCd"),
                        customerObject.optInt("id")
                );
                customers.add(customer);
            }
        }

        return customers;
    }

    public void clearLocalStorage() {
        ((JavascriptExecutor) driver).executeScript("localStorage.clear();");
    }
}
