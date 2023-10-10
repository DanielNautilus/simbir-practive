package org.helpers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortHelper {

    public static boolean isSortedAsc(List<String> rowValues) {
        List<String> sortedCustomerNames = new ArrayList<>(rowValues);
        Collections.sort(sortedCustomerNames);
        return rowValues.equals(sortedCustomerNames);
    }

    public static boolean isSortedDesc(List<String> rowValues) {
        List<String> sortedCustomerNames = new ArrayList<>(rowValues);
        sortedCustomerNames.sort(Collections.reverseOrder());
        return rowValues.equals(sortedCustomerNames);
    }
}
